package com.talentica.resam.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class IdentityError extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new error.
	 * 
	 * @param cause the cause of the new error.
	 */
	public IdentityError(Throwable cause) {
		super(Response
				.status(Response.Status.BAD_REQUEST)
				.entity(new ErrorReport(cause))
				.build());
	}
	
	/**
	 * Wrapper for reporting error to client in webservice
	 * (JSON/XML) friendly way.
	 */
	public static class ErrorReport {
		private final String type;
		private final String description;
		
		public ErrorReport(String type, String description) {
			this.type = type;
			this.description = description;
		}
		
		public ErrorReport(Throwable error) {
			this(error.getClass().getName(), error.getMessage());
		}

		public String getType() {
			return type;
		}

		public String getDescription() {
			return description;
		}
	}
	
}

