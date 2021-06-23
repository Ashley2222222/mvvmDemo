package com.commlib.util;

import org.apache.commons.net.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

	private static String mKey = "Gciabc@123456789";

	private static Encryption mEncryption;

	private Encryption() {
	}

	public static Encryption getInstance() {
		if (mEncryption == null) {
			mEncryption = new Encryption();
		}
		return mEncryption;
	}

	// 加密
	public static String Encrypt(String sSrc) throws Exception {
		if (mKey == null) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (mKey.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = mKey.getBytes("utf-8");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

		return new Base64().encodeToString(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}

	// 解密
	public static String Decrypt(String sSrc) throws Exception {
		try {
			// 判断Key是否正确
			if (mKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (mKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = mKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = new Base64().decode(sSrc);// 先用base64解密
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original, "utf-8");
				return originalString;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	// base64加密
	public static String Base64Encrypt(String sSrc) {
		if (sSrc == null) {
			return null;
		}
		return new Base64().encodeToString(sSrc.getBytes()).trim();
	}

	// base64解密
	public static String Base64Decrypt(String sSrc) {
		if (sSrc == null) {
			return null;
		}
		byte[] encrypted1 = new Base64().decode(sSrc);
		return new String(encrypted1);
	}

}
