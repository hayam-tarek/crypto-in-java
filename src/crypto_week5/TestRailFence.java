package crypto_week5;

public class TestRailFence {

	public static void main(String[] args) {
		
		RailFenceBasic obj = new RailFenceBasic(5);
		String plainText = "crypto#graphy#and#network#security";
		
		System.out.println("plainText >>>: " + plainText);
		System.out.println("After encrypt: " + obj.encrypt(plainText));
		System.out.println("After decrypt: " + obj.decrypt(obj.encrypt(plainText)));
		System.out.println();
	}

}
