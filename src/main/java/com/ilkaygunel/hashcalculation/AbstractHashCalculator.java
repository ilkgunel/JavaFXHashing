package com.ilkaygunel.hashcalculation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class AbstractHashCalculator {

	public String hashAlgorithm = "MD5";
	
	public String calculateHash(String textToHash) {
		StringBuffer sb16 = new StringBuffer();
		StringBuffer sb32 = new StringBuffer();
		try {
			MessageDigest messageDigestObjesi = MessageDigest.getInstance(hashAlgorithm);
			messageDigestObjesi.update(textToHash.getBytes());
			byte[] messageDigestDizisi = messageDigestObjesi.digest();

			for (int i = 0; i < messageDigestDizisi.length; i++) {
				sb16.append(Integer.toString((messageDigestDizisi[i] & 0xfd) + 0x100, 16).substring(1));
				sb32.append(Integer.toString((messageDigestDizisi[i] & 0xff) + 0x100, 32));
			}
			System.out.println("Parolanın Şifrelenmiş Hali:(16) " + sb16.toString());
			System.out.println("Parolanın Şifrelenmiş Hali:(32) " + sb32.toString());
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
		return sb32.toString();
	}
}
