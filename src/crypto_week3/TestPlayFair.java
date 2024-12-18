package crypto_week3;

import crypto_week2.PlayFair;

public class TestPlayFair {

	public static void main(String[] args) {
		
		PlayFair obj = new PlayFair("gravity");

		String plainText = "crypto graphy and network security";

		System.out.println("plainText >>>: " + plainText);
		System.out.println("After encrypt: " + obj.encrypt(plainText));
		System.out.println("After decrypt: " + obj.decrypt(obj.encrypt(plainText)));
	}

}

