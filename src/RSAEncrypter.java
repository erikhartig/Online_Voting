import java.math.BigInteger;
import java.util.ArrayList;

public class RSAEncrypter {

	private BigInteger n;
	private BigInteger e;
	private String location;
	private int blockSize;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public RSAEncrypter(BigInteger nTemp, BigInteger eTemp, String locationTemp){
		n = nTemp;
		e = eTemp;
		location = locationTemp;
		blockSize = RSAEncryptionUtils.getBlockSize(n);
	}
	
	public String RSAEncrypt(String message){
		ArrayList<BigInteger> blocks = RSAEncryptionUtils.getBlocksBI(message, blockSize);
		String encryptedMessage = "";
		for(int x=0; blocks.size()<x; x++){
			String currentNumEncrypted = encryptBigInteger(blocks.get(x));
			encryptedMessage = encryptedMessage + currentNumEncrypted;
		}
		return encryptedMessage;
	}
	
	private String encryptBigInteger(BigInteger numToEncrypt){
		BigInteger exponentiatedNum = numToEncrypt.modPow(e,n);
		String exNum = exponentiatedNum.toString();
		String originalNum = numToEncrypt.toString();
		String finalNum = exNum;
		if(exNum.length() < originalNum.length()){
			int zerosToAdd = originalNum.length() - exNum.length();
			String zeros = "";
			for(int x=0; zerosToAdd<x; x++){
				zeros = zeros + "0";
			}
			finalNum = zeros + finalNum;
		}
		return finalNum;
	}
	
	public String getLocation(){
		return location;
	}

}
