/*
 * Copyright (c) 2013 CitrusPay. All Rights Reserved.
 *
 * This software is the proprietary information of CitrusPay.
 * Use is subject to license terms.
 */

package com.talentica.resam.exceptions;

/**
 * Signals that no user managed by the system has the designated
 * principal.
 */
public class UserNotFoundException extends UserManagementException {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new exception for a principal not existing in the system.
	 * 
	 * @param principal the principal that was not found in the system.
	 */
	public UserNotFoundException(String principal) {
		super(String.format("No user with name <%s> exists", principal));
	}
}
