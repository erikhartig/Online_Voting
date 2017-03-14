//@Authors Owen Galvin and Erik Hartig
//@Date 2/26/2017

//This class uses a private key to dencrypt cyphertext messages into plaintext

import java.math.BigInteger;
/**
 * Class that does RSA decryption for a specific private key n and
 * d.
 * @author Erik Hartig
 * @date 3/10/17
 */
public class RSADecrypter {

	private BigInteger n;
	private BigInteger d;
	private int blockSize;

	/**
	 * Stores information about the values needed for decryption of a specific
	 * RSA key.
	 * @param nTemp n in a standard RSA implementation aka p*q
	 * @param dTemp d in a standard RSA implementation, the number the message is taken up to in decryption
	 */
	public RSADecrypter(BigInteger nTemp, BigInteger dTemp) {
		n = nTemp;
		d = dTemp;
		blockSize = RSAEncryptionUtils.getBlockSize(n);
	}

	/** 
	 * Decrypts a message encrypted using the public key that matches
	 * the private key stored in the object and return the plaintext as 
	 * a String.  Note uses ASCII character encoding.
	 * @param message encrypted message
	 * @return the plaintext
	 */
	public String RSADecrypt(String message) {
		String decryptedMessage = "";
		
		BigInteger exponentiatedNum = new BigInteger(message).modPow(d, n);
		String binary = RSAEncryptionUtils.decimalToBinaryBI(exponentiatedNum);
		while(binary.length()%8 !=0){
			binary = binary + "0";
		}
		
		while (binary.length() > blockSize) {
			String block = binary.substring(0, blockSize);
			decryptedMessage = decryptedMessage + decryptBigInteger(block);
			binary = binary.substring(blockSize);
		}
		decryptedMessage = decryptedMessage + decryptBigInteger(binary);
		return decryptedMessage;
	}

	/**
	 * Decrypts a single block encrypted using the RSA method.  Note the 
	 * block size is determined based on n when the object is initialized.
	 * @param block the encrypted block to be decrypted
	 * @return the decrypted block
	 */
	private String decryptBigInteger(String block) {
		String message = "";
		while (block.length() > 8) {
			int character = RSAEncryptionUtils.binaryToDecimal(block.substring(0, 8));
			char charToBeAdded = (char) character;
			block = block.substring(8);
			message = message + charToBeAdded;
		}
		int character = RSAEncryptionUtils.binaryToDecimal(block);
		char charToBeAdded = (char) character;
		message = message + charToBeAdded;
		return message;
	}
}
