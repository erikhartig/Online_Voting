import java.math.*;

public class RSAKeyPair {
	private String privateKey;
	private String publicKey;
	
	RSAKeyPair() {
		generateKeyPair();
	}
	
	private void generateKeyPair() {
		BigInteger p = generatePrime();
		BigInteger q = generatePrime();
		
	}
	
	private BigInteger generatePrime() {
		return null;
	}
	
	public String getPrivateKey() {
		return null;
		
	}
	
	public String getPublicKey() {
		return null;
	}

}
