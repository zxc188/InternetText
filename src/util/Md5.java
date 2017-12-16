package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
	public String GetMD5Code(String msg) {

		String resultString = null;

		try {

			resultString = new String(msg);

			MessageDigest md = MessageDigest.getInstance("MD5");

			resultString = byteToString(md.digest(msg.getBytes()));

		} catch (NoSuchAlgorithmException ex) {

			ex.printStackTrace();

		}

		return resultString;

	}

	public String byteToString(byte[] bytes) {

		StringBuffer md5str = new StringBuffer();

		// 把数组每一字节换成16进制连成md5字符串

		int digital;

		for (int i = 0; i < bytes.length; i++) {

			digital = bytes[i];

			if (digital < 0) {

				digital += 256;

			}

			if (digital < 16) {

				md5str.append("0");

			}

			md5str.append(Integer.toHexString(digital));

		}

		return md5str.toString().toUpperCase();

	}
}
