package crypto_week2;

public class TestMonoAlphabetic {

	public static void main(String[] args) {
		char keys[] = { 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L',
				'Z', 'X', 'C', 'V', 'B', 'N', 'M' };

		MonoAlphabetic obj = new MonoAlphabetic(keys);
		
		String plainText = "live the moment";

		System.out.println("plainText >>>: " + plainText);
		System.out.println("After encrypt: " + obj.encrypt(plainText));
		System.out.println("After decrypt: " + obj.decrypt(obj.encrypt(plainText)));

	}

}
