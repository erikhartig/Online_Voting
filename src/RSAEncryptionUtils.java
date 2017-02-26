import java.math.BigInteger;
import java.util.ArrayList;

public class RSAEncryptionUtils {
	public static void main(String args[]){
		String test = "hello";
		String binary = messageToBinary(test, 32);
		System.out.println(binary);
		System.out.println(binaryToDecimal("10101"));
	}
	
	public static ArrayList<Integer> getBlocks(String message, int blockSize){
		ArrayList<Integer> blocks = new ArrayList<Integer>();
		while(message.length()> blockSize){
			String block = message.substring(0, blockSize);
			Integer decimalBlock = binaryToDecimal(block);
			blocks.add(decimalBlock); 
			message = message.substring(blockSize);
		}
		Integer decimalBlock = binaryToDecimal(message);
		blocks.add(decimalBlock);
		return blocks;
	}
	
	public static ArrayList<BigInteger> getBlocksBI(String message, int blockSize){
		ArrayList<BigInteger> blocks = new ArrayList<BigInteger>();
		while(message.length()> blockSize){
			String block = message.substring(0, blockSize);
			BigInteger decimalBlock = binaryToDecimalBI(block);
			blocks.add(decimalBlock);
			message = message.substring(blockSize);
		}
		BigInteger decimalBlock = binaryToDecimalBI(message);
		blocks.add(decimalBlock);
		return blocks;
	}
	public static int getBlockSize(int n){
		double i = 0;
		while(Math.pow(2, i) < n){
			i++;
		}
		i--;
		return (int)i;
	}
	public static int getBlockSize(BigInteger n){
		int i = 0;
		BigInteger test = new BigInteger("2");
		while(test.pow(i).compareTo(n)==-1){
			i++;
		}
		i--;
		return i;
	}
	public static String messageToBinary(String message, int minBits){
		String finalMessage = "";
		for(int x=0; x<message.length(); x++){
			Character temp = message.charAt(x);
			finalMessage = finalMessage + charToBinary(temp,8) + " ";
		}
		return finalMessage;
	}
	public static int binaryToDecimal(String binary){
		int decimal = 0;
		for(int x=0; x<binary.length(); x++){
			if(binary.charAt(binary.length()-x-1) == '1'){
				decimal += Math.pow(2, (double)x);
			}
		}
		return decimal;
	}
	
	public static BigInteger binaryToDecimalBI(String binary){
		BigInteger decimal = BigInteger.ZERO;
		for(int x=0; x<binary.length(); x++){
			if(binary.charAt(binary.length()-x-1) == '1'){
				decimal = decimal.add(new BigInteger(((Double)Math.pow(2, (double)x)).toString()));
			}
		}
		return decimal;
	}
	public static int charToDecimal(Character ch){
		return (int) ch;
	}
	public static String charToBinary(Character ch, int minBits){
		int numValue = (int) ch;
		String noPadBinary = Integer.toBinaryString(numValue);
		if(noPadBinary.length() < minBits){
			int bitsToAdd = minBits - noPadBinary.length();
			String leadingZeros = "";
			for(int x=0; x<bitsToAdd; x++){
				leadingZeros = leadingZeros + "0";
			}
			return leadingZeros + noPadBinary;
		}
		else if(noPadBinary.length()> minBits){
			System.out.println("error");
			return noPadBinary;
		}
		else
			return noPadBinary;
	}
}
