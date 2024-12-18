package crypto_week2;

public class TestCaesar {

	public static void main(String[] args) {
		
		Caesar obj = new Caesar(23);

		String plainText = "abcdefghijklmnopqrstuvwxyz";

		System.out.println("plainText >>>: " + plainText);
		System.out.println("After encrypt: " + obj.encrypt(plainText));
		System.out.println("After decrypt: " + obj.decrypt(obj.encrypt(plainText)));
	}

}
