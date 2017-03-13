//@Authors Owen Galvin and Erik Hartig
//@Date 2/26/2017

//This class uses a private key to dencrypt cyphertext messages into plaintext

import java.math.BigInteger;

public class RSADecrypter {

	private BigInteger n;
	private BigInteger d;
	private int blockSize;

	public RSADecrypter(BigInteger nTemp, BigInteger dTemp) {
		n = nTemp;
		d = dTemp;
		blockSize = RSAEncryptionUtils.getBlockSize(n);
	}

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
