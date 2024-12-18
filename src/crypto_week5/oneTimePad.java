package crypto_week5;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class oneTimePad {

	private String key = "";

	public oneTimePad(int len) {
		generateKey(len);
	}

	public void generateKey(int len) {
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			char randomizedCharacter = (char) (random.nextInt(26) + 'a');
			key += randomizedCharacter;
		}
		System.err.println(key);
	}

	public String encrypt(String plainText) {

		plainText = handlePT(plainText);
		if (plainText.length() != key.length()) {
			return null;
		}
		String cipherText = "";
		for (int i = 0; i < plainText.length(); i++) {
			cipherText += (char) (((plainText.charAt(i) - 'a') + (key.charAt(i) - 'a')) % 26 + 'a');
		}
		return cipherText.toUpperCase();
	}

	public String handlePT(String pt) { // to delete spaces
		pt = pt.toLowerCase();
		String res = "";
		for (int i = 0; i < pt.length(); i++) {
			char c = pt.charAt(i);
			if (c < 'a' || c > 'z')
				continue;
			res += pt.charAt(i);
		}
		return res;
	}

	public String decrypt(String cipherText) {
		
		cipherText = cipherText.toLowerCase();
		if (cipherText.length() != key.length()) {
			return null;
		}
		String plainText = "";
		for (int i = 0; i < cipherText.length(); i++) {
			plainText += (char) (((cipherText.charAt(i) - 'a') - (key.charAt(i) - 'a') + 26) % 26 + 'a');
		}
		return plainText;
	}

	public File encrypt(File plainTextFile) throws IOException {
		Scanner read = new Scanner(plainTextFile);

		File output = new File("encrypt.txt");
		PrintWriter write = new PrintWriter(output);

		while (read.hasNext()) {
			write.println(encrypt(read.nextLine()));
		}

		write.flush();
		write.close();
		read.close();

		return output;
	}

	public File decrypt(File cipherTextFile) throws IOException {
		Scanner read = new Scanner(cipherTextFile);

		File output = new File("decrypt.txt");
		PrintWriter write = new PrintWriter(output);

		while (read.hasNext()) {
			write.println(decrypt(read.nextLine()));
		}

		write.flush();
		write.close();
		read.close();

		return output;
	}

}