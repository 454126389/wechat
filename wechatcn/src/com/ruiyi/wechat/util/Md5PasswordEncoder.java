package com.ruiyi.wechat.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5PasswordEncoder {

	private static final char[] HEX = {

	'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'

	};

	protected static final MessageDigest getMessageDigest() throws IllegalArgumentException {

		try {

			return MessageDigest.getInstance("MD5");

		} catch (NoSuchAlgorithmException e) {

			throw new IllegalArgumentException("No such algorithm [MD5]");

		}

	}

	/**
	 *
	 * ???????
	 *
	 * rawPass ??????
	 *
	 * salt ??????
	 **/

	public static String encodePassword(String rawPass, Object salt) {

		String saltedPass = mergePasswordAndSalt(rawPass, salt, false);

		MessageDigest messageDigest = getMessageDigest();

		byte[] digest;

		try {

			digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));

		} catch (UnsupportedEncodingException e) {

			throw new IllegalStateException("UTF-8 not supported!");

		}

		// "stretch" the encoded value if configured to do so

		for (int i = 1; i < 1; i++) {

			digest = messageDigest.digest(digest);

		}

		return new String(encode(digest));

	}

	protected static char[] encode(byte[] bytes) {

		final int nBytes = bytes.length;

		char[] result = new char[2 * nBytes];

		int j = 0;

		for (int i = 0; i < nBytes; i++) {

			// Char for top 4 bits

			result[j++] = HEX[(0xF0 & bytes[i]) >>> 4];

			// Bottom 4

			result[j++] = HEX[(0x0F & bytes[i])];

		}

		return result;

	}

	protected static String mergePasswordAndSalt(String password, Object salt, boolean strict) {

		if (password == null) {

			password = "";

		}

		if (strict && (salt != null)) {

			if ((salt.toString().lastIndexOf("{") != -1) || (salt.toString().lastIndexOf("}") != -1)) {

				throw new IllegalArgumentException("Cannot use { or } in salt.toString()");

			}

		}

		if ((salt == null) || "".equals(salt)) {

			return password;

		} else {

			return password + "{" + salt.toString() + "}";

		}

	}



    public Md5PasswordEncoder() {
    }
}