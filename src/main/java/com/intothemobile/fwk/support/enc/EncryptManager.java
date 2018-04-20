package com.intothemobile.fwk.support.enc;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptManager {
	private static final Logger logger = LoggerFactory.getLogger(EncryptManager.class);

	private String secretKey;
	
	public String encryptAES(String s) throws Exception {
		return this.encryptAES(s, secretKey);
	}
	
	public String decryptAES(String s) throws Exception {
		return this.decryptAES(s, secretKey);
	}

	public String encryptAES(String s, String key) throws Exception {
		String encrypted = null;
		
		if (key.length() > 16) {
			key = key.substring(0, 16);
		}

		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		encrypted = byteArrayToHex(cipher.doFinal(s.getBytes()));

		if (logger.isDebugEnabled()) { logger.debug(s + " => " + encrypted); }

		return encrypted;
	}

	public String decryptAES(String s, String key) throws Exception {
		String decrypted = null;
		
		if (key.length() > 16) {
			key = key.substring(0, 16);
		}

		if (logger.isDebugEnabled()) { logger.debug(s + " => " + key); }
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		decrypted = new String(cipher.doFinal(hexToByteArray(s)));

		if (logger.isDebugEnabled()) { logger.debug(s + " => " + decrypted); }

		return decrypted;
	}

	private byte[] hexToByteArray(String s) {
		byte[] retValue = null;
		if (s != null && s.length() != 0) {
			retValue = new byte[s.length() / 2];
			for (int i = 0; i < retValue.length; i++) {
				retValue[i] = (byte) Integer.parseInt(
						s.substring(2 * i, 2 * i + 2), 16);
			}
		}
		return retValue;
	}

	private String byteArrayToHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		for (int i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10) {
				strbuf.append("0");
			}
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
