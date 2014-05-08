package com.talentica.resam.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class ResamLoginFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	private String defaultFailureUrl;

	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		super.setDefaultFailureUrl(defaultFailureUrl);
		this.defaultFailureUrl = defaultFailureUrl;
	}

	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		// Update the max no of failure attempts if valid user available and its
		// bad creditials exception
		if (exception instanceof BadCredentialsException) {

		}

		if (defaultFailureUrl == null) {
			// logger.debug("No failure URL set, sending 401 Unauthorized error");

			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"Authentication Failed: " + exception.getMessage());
		} else {
			saveException(request, exception);

			// logger.debug("Redirecting to " + defaultFailureUrl);
			getRedirectStrategy().sendRedirect(request, response,
					defaultFailureUrl);

		}
	}

}
