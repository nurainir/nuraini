
package com.romdhoni.hashfile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Getting digest of a file using algorithm defined in java.security.MessageDigest.
 * This file is written under information got from:
 * http://rgagnon.com/javadetails/java-0596.html for getHexString algorithm
 * http://www.javalobby.org/java/forums/t84420.html for Simple MD5Sum algorithm
 * @author abufauziya
 * @since Jan 28, 2011 07:57 PM (GMT+7)
 * @version 1.0
 */
public class HashFile {

	private static final byte[] HEX_CHAR_TABLE = {
    (byte)'0', (byte)'1', (byte)'2', (byte)'3',
    (byte)'4', (byte)'5', (byte)'6', (byte)'7',
    (byte)'8', (byte)'9', (byte)'a', (byte)'b',
    (byte)'c', (byte)'d', (byte)'e', (byte)'f'
  };

  private static String getHexString(byte[] raw) throws UnsupportedEncodingException
  {
    byte[] hex = new byte[2 * raw.length];
    int index = 0;

    for (byte b : raw) {
      int v = b & 0xFF;
      hex[index++] = HEX_CHAR_TABLE[v >>> 4];
      hex[index++] = HEX_CHAR_TABLE[v & 0xF];
    }
    return new String(hex, "ASCII");
  }


	public static final String MODE_MD5 = "MD5";
	public static final String MODE_SHA1 = "SHA-1";
	public static final String MODE_SHA224 = "SHA-224";
	public static final String MODE_SHA256 = "SHA-256";
	public static final String MODE_SHA384 = "SHA-384";
	public static final String MODE_SHA512 = "SHA-512";

	/**
	 * Get hexadecimal digest of file using mode defined in class java.security.MessageDigest.
	 * @param path Path of file that will be digest
	 * @param algo Algorithm used to digest the file
	 * @return String digest of file
	 * @throws NoSuchAlgorithmException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String get(String path, String algo) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		FileInputStream fileInputStream = new FileInputStream(path);
		MessageDigest md = MessageDigest.getInstance(algo);
		byte[] buffer = new byte[8192];
		int read = 0;
		while ((read = fileInputStream.read(buffer)) > 0) {
			md.update(buffer, 0, read);
		}
		return getHexString(md.digest());
		// return (new java.math.BigInteger(1, md.digest())).toString(16);
	}

	/**
	 * Get hexadecimal digest of file using MD5 algorithm
	 * @param path Path of file that will be digest
	 * @return String digest of file
	 * @throws NoSuchAlgorithmException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String md5sum(String path) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		return get(path, MODE_MD5);
	}

	/**
	 * Get hexadecimal digest of file using SHA-1 algorithm
	 * @param path Path of file that will be digest
	 * @return String digest of file
	 * @throws NoSuchAlgorithmException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String sha1sum(String path) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		return get(path, MODE_SHA1);
	}

	/**
	 * Get hexadecimal digest of file using SHA-128 algorithm
	 * @param path Path of file that will be digest
	 * @return String digest of file
	 * @throws NoSuchAlgorithmException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String sha224sum(String path) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		return get(path, MODE_SHA224);
	}

	/**
	 * Get hexadecimal digest of file using SHA-256 algorithm
	 * @param path Path of file that will be digest
	 * @return String digest of file
	 * @throws NoSuchAlgorithmException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String sha256sum(String path) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		return get(path, MODE_SHA256);
	}

	/**
	 * Get hexadecimal digest of file using SHA-384 algorithm
	 * @param path Path of file that will be digest
	 * @return String digest of file
	 * @throws NoSuchAlgorithmException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String sha384sum(String path) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		return get(path, MODE_SHA384);
	}

	/**
	 * Get hexadecimal digest of file using SHA-512 algorithm
	 * @param path Path of file that will be digest
	 * @return String digest of file
	 * @throws NoSuchAlgorithmException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String sha512sum(String path) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		return get(path, MODE_SHA512);
	}

}
