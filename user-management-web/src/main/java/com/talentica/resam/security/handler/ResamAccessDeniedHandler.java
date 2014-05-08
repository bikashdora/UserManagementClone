package com.talentica.resam.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;

public class ResamAccessDeniedHandler implements AccessDeniedHandler {

	private String accessDeniedUrl;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public ResamAccessDeniedHandler() {
	}

	public ResamAccessDeniedHandler(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {

		redirectStrategy.sendRedirect(request, response, accessDeniedUrl);
		request.getSession().setAttribute("message",
				"You do not have permission to access this page!");

	}

}
