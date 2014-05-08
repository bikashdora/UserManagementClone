package com.talentica.resam.auth.encryption;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class PasswordEncryptor {

	
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

	public static String generateHMAC(String data, String hexEncodedKey) {
		String result = "";
		try {
			byte[] keyBytes = hexEncodedKey.getBytes();
			SecretKeySpec signingKey = new SecretKeySpec(keyBytes,
					HMAC_SHA1_ALGORITHM);
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes());

			byte[] hexBytes = new Hex().encode(rawHmac);
			result = new String(hexBytes, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
