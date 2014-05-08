/*
 * Copyright (c) 2013 CitrusPay. All Rights Reserved.
 *
 * This software is the proprietary information of CitrusPay.
 * Use is subject to license terms.
 */

package com.talentica.resam.exceptions;

public class SignupTaskException extends UserManagementException {

	private static final long serialVersionUID = -154506744088113287L;

	public SignupTaskException(String principle) {
		super("Prepaid sign up task failed for user " + principle);
	}
}
