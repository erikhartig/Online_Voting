//@Authors Owen Galvin and Erik Hartig
//@Date 2/26/2017

//This class generates an RSA key pair when constructed, which can be accessed using
//the getPrivateKey and getPublicKey methods

import java.math.*;
import java.util.ArrayList;
import java.util.Random;

public class RSAKeyPair {
	private ArrayList<BigInteger> privateKey;
	private ArrayList<BigInteger> publicKey;

	RSAKeyPair() {
		privateKey = new ArrayList<>();
		publicKey = new ArrayList<>();

		generateKeyPair();
	}

	// This method generates the 1024 bit prime numbers needed for the keys,
	// from which the private and public keys are calculated
	private void generateKeyPair() {
		Random rand1 = new Random();
		Random rand2 = new Random();
		BigInteger p = BigInteger.probablePrime(1024, rand1);
		BigInteger q = BigInteger.probablePrime(1024, rand2);

		BigInteger n = p.multiply(q);
		BigInteger e = BigInteger.valueOf(65537);

		generatePrivateKey(p, q, n, e);
		generatePublicKey(n, e);
	}

	// this method uses the two primes to generate the entire private key,
	// setting them as [n, d].ALERT: Values p, q, lcm, and d MUST BE KEPT
	// PRIVATE!
	private void generatePrivateKey(BigInteger p, BigInteger q, BigInteger n, BigInteger e) {
		// find the gcd(p-1, q-1)
		BigInteger pMinus1 = p.subtract(BigInteger.valueOf(1));
		BigInteger qMinus1 = q.subtract(BigInteger.valueOf(1));
		BigInteger gcd = pMinus1.gcd(qMinus1);

		// find the lcm(p-1, q-1) = (p-1)(q-1)/gcd
		BigInteger lcm = (pMinus1.multiply(qMinus1)).divide(gcd);

		// find the modular multiplicative inverse of e mod lcm
		BigInteger d = e.modInverse(lcm);

		privateKey.add(0, n);
		privateKey.add(1, d);
	}

	// this method uses the two primes to generate the entire public key,
	// setting them as [n, e]
	private void generatePublicKey(BigInteger n, BigInteger e) {
		publicKey.add(0, n);
		publicKey.add(1, e);
	}

	// this method returns the private key
	public ArrayList<BigInteger> getPrivateKey() {
		return privateKey;
	}

	// this method returns the public key
	public ArrayList<BigInteger> getPublicKey() {
		return publicKey;
	}

}
