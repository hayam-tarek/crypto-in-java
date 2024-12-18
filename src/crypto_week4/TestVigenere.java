package crypto_week4;

public class TestVigenere {

	public static void main(String[] args) {
		
		Vigenere obj = new Vigenere("gravity");

		String plainText = "crypto graphy and network security";

		System.out.println("plainText >>>: " + plainText);
		System.out.println("After encrypt: " + obj.encrypt(plainText));
		System.out.println("After decrypt: " + obj.decrypt(obj.encrypt(plainText)));
		System.out.println();
		
		
	}
}
