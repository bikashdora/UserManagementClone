package com.talentica.resam.auth.encryption;

import org.springframework.security.authentication.encoding.PasswordEncoder;


public class ResamPasswordEncoder implements PasswordEncoder {

	public String encodePassword(String rawPass, Object salt) {
		return MD5Generator.MD5(rawPass);
	}

	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		if (encPass.equals(encodePassword(rawPass, null))) {
			return true;
		}
		return false;
	}

}
