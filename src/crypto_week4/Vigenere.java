package crypto_week4;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Vigenere {

	private final String key;

	public Vigenere(String key) {
		key = key.toUpperCase();
		this.key = key;
	}

	public String encrypt(String plainText) {
		String cipherText = "";
		plainText = plainText.toUpperCase();
		for (int i = 0, k = 0; i < plainText.length(); i++) {
			char c = plainText.charAt(i);
			if (c < 'A' || c > 'Z')
				continue;
			cipherText += (char) (((c - 'A') + (key.charAt(k) - 'A')) % 26 + 'A');
			k = ++k % (key.length());
		}
		return cipherText;
	}

	public String decrypt(String cipherText) {
		String plainText = "";
		cipherText = cipherText.toUpperCase();
		for (int i = 0, k = 0; i < cipherText.length(); i++) {
			char c = cipherText.charAt(i);
			if (c < 'A' || c > 'Z')
				continue;
			plainText += (char) (((c - 'A') - (key.charAt(k) - 'A') + 26) % 26 + 'A');
			k = ++k % (key.length());
		}
		return plainText.toLowerCase();
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
