package com.talentica.resam.auth.encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Generator {

	private static final String P_S_W_D_S_A_L_T = "$apr1$s44vbCxV$CABUK112@$@4&#%^$*m6dfrtyu";

	private static final String P_S_W_D_S_H_A_K_E_Y = "#FV345sdg#$%^&gfhh45Vjyu&^cvhPOXY";

	private static String convertedToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < data.length; i++) {
			int halfOfByte = (data[i] >>> 4) & 0x0F;
			int twoHalfBytes = 0;

			do {
				if ((0 <= halfOfByte) && (halfOfByte <= 9)) {
					buf.append((char) ('0' + halfOfByte));
				}

				else {
					buf.append((char) ('a' + (halfOfByte - 10)));
				}

				halfOfByte = data[i] & 0x0F;

			} while (twoHalfBytes++ < 1);
		}
		return buf.toString();
	}

	public static String MD5(String text) {
		// generate hmac of the actual password
		String hmacPswd = PasswordEncryptor.generateHMAC(text,
				P_S_W_D_S_H_A_K_E_Y);
		// salt the hmac
		String pswdText = hmacPswd + P_S_W_D_S_A_L_T;
		String hashed_text = "";
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			byte[] md5 = new byte[64];
			md.update(pswdText.getBytes("iso-8859-1"), 0, pswdText.length());
			md5 = md.digest();
			// rehash: generate the password to store
			hashed_text = convertedToHex(md5);
		} catch (NoSuchAlgorithmException e) {

		} catch (UnsupportedEncodingException e) {

		}
		return hashed_text;
	}

}
