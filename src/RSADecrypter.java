import java.math.BigInteger;
import java.util.ArrayList;

public class RSADecrypter {
	
	private BigInteger n;
	private BigInteger d;
	private int blockSize;
	
	public RSADecrypter(BigInteger nTemp, BigInteger dTemp){
		n = nTemp;
		d = dTemp;
		blockSize = RSAEncryptionUtils.getBlockSize(n);
	}
	public String decryptBigInteger(String message){
		String decryptedMessage = "";
		while(message.length()> blockSize){
			String block = message.substring(0, blockSize);
			BigInteger decimalBlock = new BigInteger(block);
			decryptedMessage = decryptedMessage + decryptBigInteger(decimalBlock);
			message = message.substring(blockSize);
		}
		BigInteger decimalBlock = new BigInteger(message);
		decryptedMessage = decryptedMessage + decryptBigInteger(decimalBlock);
		return decryptedMessage;
	}
	
	public String decryptBigInteger(BigInteger numToDecrypt){
		BigInteger exponentiatedNum = numToDecrypt.modPow(d,n);
		String binary = exponentiatedNum.toString(2);
		String message = "";
		while(binary.length()>32){
			int character = RSAEncryptionUtils.binaryToDecimal(binary.substring(0, 32));
			char charToBeAdded = (char) character;
			binary = binary.substring(32);
			message = message + charToBeAdded;
		}
		int character = RSAEncryptionUtils.binaryToDecimal(binary);
		char charToBeAdded = (char) character;
		message = message + charToBeAdded;
		return message;
	}
}
