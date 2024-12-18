package crypto_week5;

public class TestOneTimePad {

	public static void main(String[] args) {

		String plainText = "cryptographyandnetworksecurity";
		oneTimePad obj = new oneTimePad(plainText.length());

		System.out.println("plainText >>>: " + plainText);
		System.out.println("After encrypt: " + obj.encrypt(plainText));
		System.out.println("After decrypt: " + obj.decrypt(obj.encrypt(plainText)));
		System.out.println();

	}

}
