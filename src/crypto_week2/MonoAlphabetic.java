package crypto_week2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MonoAlphabetic {

	private final char[] key;

	public static char normalChar[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public MonoAlphabetic(char[] key) {
		this.key = key;
	}

	public String encrypt(String plainText) {
		String cipherText = "";
		for (int i = 0; i < plainText.length(); i++) {
			for (int j = 0; j < 26; j++) {
				if (plainText.charAt(i) == normalChar[j]) {
					cipherText += key[j];
					break;
				}
				if (plainText.charAt(i) < 'a' || plainText.charAt(i) > 'z') {
					cipherText += plainText.charAt(i);
					break;
				}
			}
		}
		return cipherText;
	}

	public String decrypt(String cipherText) {
		String plainText = "";
		for (int i = 0; i < cipherText.length(); i++) {
			for (int j = 0; j < 26; j++) {
				if (cipherText.charAt(i) == key[j]) {
					plainText += normalChar[j];
					break;
				}
				if (cipherText.charAt(i) < 'A' || cipherText.charAt(i) > 'Z') {
					plainText += cipherText.charAt(i);
					break;
				}
			}
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
