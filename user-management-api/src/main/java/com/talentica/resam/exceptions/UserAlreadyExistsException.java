/*
 * Copyright (c) 2013 CitrusPay. All Rights Reserved.
 *
 * This software is the proprietary information of CitrusPay.
 * Use is subject to license terms.
 */

package com.talentica.resam.exceptions;

/**
 * Signals that a user with same principal already exists in the system.
 */
public class UserAlreadyExistsException extends UserManagementException {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new exception for a a principal already managed by
	 * the system.
	 * 
	 * @param principal the already existing principal.
	 */
	public UserAlreadyExistsException(String message) {
		super(String.format("User with name <%s> already exists", message));
	}
	
}
