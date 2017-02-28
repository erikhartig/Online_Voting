//@Authors Owen Galvin and Erik Hartig
//@Date 2/26/2017

//This class provides general utilities for RSA algorithms

import java.math.BigInteger;
import java.util.ArrayList;

public class RSAEncryptionUtils {
/*	
 		Here is an example of how to utilize this package to generate keys, encrypt a message with a public key, 
 		and decrypt it using the private key
 		
 		String plaintext = "This is a secret message that is impossible to decode";
		System.out.println("Plaintext: " + plaintext);

		RSAKeyPair keypair = new RSAKeyPair();
		
		ArrayList<BigInteger> publicKey = keypair.getPublicKey();
		RSAEncrypter testEncrypter = new RSAEncrypter(publicKey.get(0), publicKey.get(1));
		String cyphertext = testEncrypter.RSAEncrypt(plaintext);
		
		System.out.println("cyphertext: " + cyphertext);
		
		ArrayList<BigInteger> privateKey = keypair.getPrivateKey();
		RSADecrypter testDecrypter = new RSADecrypter(privateKey.get(0), privateKey.get(1));
		String hopefullyPlaintext = testDecrypter.RSADecrypt(cyphertext);
		
		System.out.println("Hopefully plaintext: " + hopefullyPlaintext);
*/

	//This method divides up the plaintext into blocks to be encrypted
	public static ArrayList<BigInteger> getBlocksBI(String message, int blockSize) {
		ArrayList<BigInteger> blocks = new ArrayList<BigInteger>();
		String binaryMessage = messageToBinary(message, 8);
		
		while (binaryMessage.length() > blockSize) {
			String block = binaryMessage.substring(0, blockSize);
			BigInteger decimalBlock = binaryToDecimalBI(block);
			blocks.add(decimalBlock);
			binaryMessage = binaryMessage.substring(blockSize);
		}
		//if the last chunk of plaintext is smaller than the blocksize, add that chunk
		BigInteger decimalBlock = binaryToDecimalBI(binaryMessage);
		blocks.add(decimalBlock);
		return blocks;
	}

	//This method calculates the blocksize that will be used based on modulus n
	public static int getBlockSize(BigInteger n) {
		int i = 0;
		BigInteger test = new BigInteger("2");
		while (test.pow(i).compareTo(n) == -1) {
			i++;
		}
		i--;
		return i;
	}

	//This method converts the ascii message ot a binary string
	public static String messageToBinary(String message, int minBits) {
		String finalMessage = "";
		for (int x = 0; x < message.length(); x++) {
			Character temp = message.charAt(x);
			finalMessage = finalMessage + charToBinary(temp, 8);
		}
		return finalMessage;
	}

	//This method converts a binary string to a decimal integer
	public static int binaryToDecimal(String binary) {
		int decimal = 0;
		for (int x = 0; x < binary.length(); x++) {
			if (binary.charAt(binary.length() - x - 1) == '1') {
				decimal += Math.pow(2, (double) x);
			}
		}
		return decimal;
	}

	//This method converts a binary string to a BigInteger decimal
	public static BigInteger binaryToDecimalBI(String binary) {
		BigInteger decimal = BigInteger.ZERO;
		BigInteger twoBI = new BigInteger("2");
		for (int x = 0; x < binary.length(); x++) {
			if (binary.charAt(binary.length() - x - 1) == '1') {
				decimal =  decimal.add(twoBI.pow(x));
			}
		}
		return decimal;
	}
	
	//This method converts a BigInteger decimal to a binary string
	public static String decimalToBinaryBI(BigInteger numToConvert){
		int biggestValue = 0;
		String binary = "";
		BigInteger twoBI = new BigInteger("2");
		while(numToConvert.compareTo(twoBI.pow(biggestValue))==1)
			biggestValue++;
		
		while(numToConvert.compareTo(BigInteger.ZERO) > 0){
			if(numToConvert.compareTo(twoBI.pow(biggestValue)) >= 0){
				binary = binary + "1";
				numToConvert = numToConvert.subtract(twoBI.pow(biggestValue));
			}
			else{
				binary = binary + "0";
			}
			biggestValue--;
		}
		return binary;
	}

	//This method converts an ascii character to its decimal representation
	public static int charToDecimal(Character ch) {
		return (int) ch;
	}

	//This method converts a character to binary, adding 0s as padding if needed
	public static String charToBinary(Character ch, int minBits) {
		int numValue = (int) ch;
		String noPadBinary = Integer.toBinaryString(numValue);
		if (noPadBinary.length() < minBits) {
			int bitsToAdd = minBits - noPadBinary.length();
			String leadingZeros = "";
			for (int x = 0; x < bitsToAdd; x++) {
				leadingZeros = leadingZeros + "0";
			}
			return leadingZeros + noPadBinary;
		} else if (noPadBinary.length() > minBits) {
			System.out.println("error");
			return noPadBinary;
		} else
			return noPadBinary;
	}
}
