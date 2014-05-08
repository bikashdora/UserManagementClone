/*
 * Copyright (c) 2013 CitrusPay. All Rights Reserved.
 *
 * This software is the proprietary information of CitrusPay.
 * Use is subject to license terms.
 */

package com.talentica.resam.exceptions;

import java.util.Arrays;

import com.talentica.resam.entity.Role;

/**
 * Signals that at least one user role is not supported by the user
 * management system.
 */
public class RoleNotSupportedException extends UserManagementException {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new exception for roles not supported by the system.
	 * 
	 * @param roles the unsupported roles.
	 */
	public RoleNotSupportedException(Role... roles) {
		super(String.format("Roles not supported: %s", Arrays.toString(roles)));
	}
	
	public RoleNotSupportedException(String message) {
		super(message);
	}

}
