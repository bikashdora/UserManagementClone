package com.talentica.resam.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import com.talentica.resam.auth.exception.ExpiredSessionException;

public class ExpiredSessionFilter extends GenericFilterBean {

	private static final Logger logger = LoggerFactory
			.getLogger(ExpiredSessionFilter.class);

	static final String FILTER_APPLIED = "__spring_security_expired_session_filter_applied";

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (request.getAttribute(FILTER_APPLIED) != null) {
			chain.doFilter(request, response);
			return;
		}

		request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		if (!uri.contains("/resources/") && !uri.equals(ctx)
				&& !uri.equals(ctx + "/") && !uri.equals(ctx + "/auth")
				&& !uri.equals(ctx + "/authfailed")
				&& !uri.equals(ctx + "/j_spring_security_logout")
				&& !uri.equals(ctx + "/j_spring_security_check")) {
			if (request.getRequestedSessionId() != null
					&& !request.isRequestedSessionIdValid()) {
				logger.error("user session expired");
				response.sendError(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED);
				throw new ExpiredSessionException();
			}
		}

		chain.doFilter(request, response);
	}
}