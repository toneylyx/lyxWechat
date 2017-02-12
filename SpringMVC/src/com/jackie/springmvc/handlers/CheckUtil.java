package com.jackie.springmvc.handlers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckUtil {

	public static final String token = "lyx";

	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[] { token, timestamp, nonce };
		// 1.paixu
		Arrays.sort(arr);
		// 2.
		StringBuffer context = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			context.append(arr[i]);
		}
		// 3.jiami
		String tmp = getSha1(context.toString());
		System.out.println("signature : " + signature);
		System.out.println("tmp : " + tmp);
		return signature.equals(tmp);
	}

	/**
	 * SHA1 ��ȫ�����㷨
	 */
	public static String getSha1(String str) {
		if (null == str || 0 == str.length()) {
			return null;
		}
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] buf = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
