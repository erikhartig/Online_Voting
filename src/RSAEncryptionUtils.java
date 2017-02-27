//@Authors Owen Galvin and Erik Hartig
//@Date 2/26/2017

//This class provides general utilities for RSA algorithms

import java.math.BigInteger;
import java.util.ArrayList;

public class RSAEncryptionUtils {
	public static void main(String args[]) {
		String plaintext = "This is a secret message that is impossible to intercept";
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
	}

	public static ArrayList<BigInteger> getBlocksBI(String message, int blockSize) {
		ArrayList<BigInteger> blocks = new ArrayList<BigInteger>();
		String binaryMessage = messageToBinary(message, 8);
		System.out.println(binaryMessage);
		
		while (binaryMessage.length() > blockSize) {
			String block = binaryMessage.substring(0, blockSize);
			BigInteger decimalBlock = binaryToDecimalBI(block);
			blocks.add(decimalBlock);
			binaryMessage = binaryMessage.substring(blockSize);
		}
		BigInteger decimalBlock = binaryToDecimalBI(binaryMessage);
		blocks.add(decimalBlock);
		return blocks;
	}

	public static int getBlockSize(int n) {
		double i = 0;
		while (Math.pow(2, i) < n) {
			i++;
		}
		i--;
		return (int) i;
	}

	public static int getBlockSize(BigInteger n) {
		int i = 0;
		BigInteger test = new BigInteger("2");
		while (test.pow(i).compareTo(n) == -1) {
			i++;
		}
		i--;
		return i;
	}

	public static String messageToBinary(String message, int minBits) {
		String finalMessage = "";
		for (int x = 0; x < message.length(); x++) {
			Character temp = message.charAt(x);
			finalMessage = finalMessage + charToBinary(temp, 8) + " ";
		}
		return finalMessage;
	}

	public static int binaryToDecimal(String binary) {
		int decimal = 0;
		for (int x = 0; x < binary.length(); x++) {
			if (binary.charAt(binary.length() - x - 1) == '1') {
				decimal += Math.pow(2, (double) x);
			}
		}
		return decimal;
	}

	public static BigInteger binaryToDecimalBI(String binary) {
		BigInteger decimal = BigInteger.ZERO;
		BigInteger twoBI = new BigInteger("2");
		for (int x = 0; x < binary.length(); x++) {
			if (binary.charAt(binary.length() - x - 1) == '1') {
				decimal = decimal.add(decimal = decimal.add(twoBI.pow(x)));
			}
		}
		return decimal;
	}

	public static int charToDecimal(Character ch) {
		return (int) ch;
	}

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
