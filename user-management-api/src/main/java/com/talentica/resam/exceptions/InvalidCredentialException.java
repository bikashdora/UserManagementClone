package com.talentica.resam.exceptions;

public class InvalidCredentialException extends
		UserManagementException {

	public InvalidCredentialException(String message) {
		super(String.format("Password for user with email <%s> invalid",
				message));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3093431774880510486L;

}
