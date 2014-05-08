package com.talentica.resam.auth.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class LoggingExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Logger logger = LoggerFactory
			.getLogger(LoggingExceptionResolver.class);
	
	@Autowired
	private LoggerHelper loggerHelper;

	
	public LoggingExceptionResolver() {
		super();
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String requestStringToLog = loggerHelper.getRequestStringToLog(request);
		if (!requestStringToLog.isEmpty())
			logger.info(requestStringToLog);
		logger.error(getStackTrace(ex));
		
		ModelAndView modelAndView = super.resolveException(request, response,
				handler, ex);
		
		if (ex instanceof AccessDeniedException) {
			modelAndView.addObject("message",
					"You do not have permission to access this page!");
		}

		return modelAndView;
	}

	public String getStackTrace(Throwable t) {
		StringWriter stringWritter = new StringWriter();
		PrintWriter printWritter = new PrintWriter(stringWritter, true);
		t.printStackTrace(printWritter);
		printWritter.flush();
		stringWritter.flush();
		return stringWritter.toString();
	}
	
	
}