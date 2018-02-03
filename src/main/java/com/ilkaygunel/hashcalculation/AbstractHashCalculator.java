package com.ilkaygunel.hashcalculation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public abstract class AbstractHashCalculator {

	public String hashAlgorithm = "MD5";

	public String calculateHash(String textToHash) {
		String myHash = "";
		try {
			MessageDigest messageDigestObjesi = MessageDigest.getInstance(hashAlgorithm);
			messageDigestObjesi.update(textToHash.getBytes());
			byte[] messageDigestDizisi = messageDigestObjesi.digest();
			myHash = DatatypeConverter.printHexBinary(messageDigestDizisi).toUpperCase();
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
		return myHash;
	}
}
