package com.talentica.resam.auth.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RandomPasswordGenerator implements PasswordGenerator {
	
	private MessageDigest md5;
	/**
	 * Generates a 8 character ([a-zA-Z]{8}) pseudo random password using the
	 * email as a seed.
	 * 
	 * As a consequence, same email will result in same password. Mobile number
	 * is ignored and can be null.
	 */
	public RandomPasswordGenerator() {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsax) {
			throw new RuntimeException(nsax);
		}
	}
	public String generate(String email) {
		PseudoRandomNumberGenerator ran = new PseudoRandomNumberGenerator(
				generateSeed(email));
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			builder.append(ran.nextLetter());
		}
		return builder.toString();
	}
	
	public int generateSeed(String data) {
		byte hash[] = md5.digest(data.getBytes());
		md5.reset();
		hash = copyOfRange(hash, hash.length - 3, hash.length);
		return new BigInteger(1, hash).intValue();
	}
	
	public static byte[] copyOfRange(byte[] original, int from, int to) {
		int newLength = to - from;
		if (newLength < 0)
			throw new IllegalArgumentException(from + " > " + to);
		byte[] copy = new byte[newLength];
		System.arraycopy(original, from, copy, 0,
				Math.min(original.length - from, newLength));
		return copy;

	}
	
	/**
	 * A pseudo random integer generator.
	 */
	public static class PseudoRandomNumberGenerator {
		private int state;

		/**
		 * Creates a new pseudo random number generator.
		 * 
		 * @param seed
		 *            the seed to start the generation from.
		 */
		public PseudoRandomNumberGenerator(int seed) {
			this.state = seed;
		}

		/**
		 * Generates the next pseudo-random integer. The number is positive in
		 * the [0-max[ interval.
		 * 
		 * @param max
		 *            the exclusive upper boundary of the interval to generate
		 *            the pseudo-random in.
		 * @return the next number in the pseudo-random suite modulo max.
		 */
		public int nextInt(int max) {
			state = 7 * state % 3001;
			return (state - 1) % max;
		}

		/**
		 * Generates the next pseudo-random alphabetical character. The letter
		 * is in the [a-zA-Z] range.
		 * 
		 * @return the next letter in the pseudo-random suite.
		 */
		public char nextLetter() {
			int n = nextInt(52);
			return (char) (n + ((n < 26) ? 'A' : 'a' - 26));
		}

	}

}
