package crypto_week2;

public class TestAffineChipher {

	public static void main(String[] args) {
		
		AffineCaesar obj = new AffineCaesar(5, 7);
		
		String plainText = "you have to solve this question";
		
		System.out.println("plainText >>>: " + plainText);
		System.out.println("After encrypt: " + obj.encrypt(plainText));
		System.out.println("After decrypt: " + obj.decrypt(obj.encrypt(plainText)));
	}
}