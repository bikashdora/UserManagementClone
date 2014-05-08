/*
 * Copyright (c) 2013 CitrusPay. All Rights Reserved.
 *
 * This software is the proprietary information of CitrusPay.
 * Use is subject to license terms.
 */

package com.talentica.resam.exceptions;

/**
 * Abstract class for user management policy exception.
 */
public abstract class UserManagementException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new user management class with user readable
	 * message.
	 * 
	 * @param message the description of the user management
	 * policy violation.
	 */
	public UserManagementException(String message) {
		super(message);
	}

}
