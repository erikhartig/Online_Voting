//@Authors Owen Galvin and Erik Hartig
//@Date 2/26/2017

//This class uses a public key to encrypt plaintext messages into cyphertext

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.*;

/**
 * A class designed to encrypt a message using standard RSA encryption
 * for a specific public key provided during the initalization of the object.
 * @author Erik Hartig
 *
 */
public class RSAEncrypter {

	private BigInteger n;
	private BigInteger e;
	private int blockSize;

	/**
	 * Creates an RSA encryption object that stores the public key and
	 * calculates the block size given n.
	 * @param nTemp n in classic RSA encryption aka p*q
	 * @param eTemp e in classic RSA encryption.
	 */
	public RSAEncrypter(BigInteger nTemp, BigInteger eTemp) {
		n = nTemp;
		e = eTemp;
		blockSize = RSAEncryptionUtils.getBlockSize(n);
	}

	/**
	 * Encrypts a message using the RSA public key provided during the 
	 * initalization of the object. Does this by breaking the message into 
	 * blocks of the calculated size and encrypting block by block.  Note uses
	 * ASCII encoding.
	 * @param message
	 * @return
	 */
	public String RSAEncrypt(String message) {
		ArrayList<BigInteger> blocks = RSAEncryptionUtils.getBlocksBI(message, blockSize);
		String encryptedMessage = "";
		for (BigInteger block : blocks) {
			String currentNumEncrypted = encryptBigInteger(block);
			encryptedMessage = encryptedMessage + currentNumEncrypted;
		}
		return encryptedMessage;
	}

	/**
	 * Encrypts a block of the specified size using RSA encryption in the 
	 * standard method.
	 * @param numToEncrypt the BigInteger block that is to be encrypted.
	 * @return the encrypted version of the block submiited.
	 */
	private String encryptBigInteger(BigInteger numToEncrypt) {
		BigInteger exponentiatedNum = numToEncrypt.modPow(e, n);
		String exNum = exponentiatedNum.toString();
		String originalNum = numToEncrypt.toString();
		String finalNum = exNum;
		if (exNum.length() < originalNum.length()) {
			int zerosToAdd = originalNum.length() - exNum.length();
			String zeros = "";
			for (int x = 0; zerosToAdd < x; x++) {
				zeros = zeros + "0";
			}
			finalNum = zeros + finalNum;
		}
		return finalNum;
	}

}
