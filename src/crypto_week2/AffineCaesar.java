package crypto_week2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AffineCaesar {

	private final int a;
	private final int b;

	public AffineCaesar(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public String encrypt(String plainText) {
		String cipherText = "";
		for (int i = 0; i < plainText.length(); i++) {
			if (!Character.isWhitespace(plainText.charAt(i))) {
				cipherText += (char) (((plainText.charAt(i) - 'a') * a + b) % 26 + 'a');
			} else {
				cipherText += plainText.charAt(i);
			}
		}
		return cipherText;
	}

	public String decrypt(String cipherText) {
		int a_inv = 0;
		for (int i = 0; i < 26; i++) {
			if (((a * i) % 26) == 1) {
				a_inv = i;
			}
		}
		String plainText = "";
		for (int i = 0; i < cipherText.length(); i++) {
			if (!Character.isWhitespace(cipherText.charAt(i))) {
				plainText += (char) ((((cipherText.charAt(i) - 'a') - b + 26) * a_inv) % 26 + 'a');
			} else {
				plainText += cipherText.charAt(i);
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
