package crypto_week3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
//import java.util.Locale;
import java.util.Scanner;

public class PlayFair {

	private final PlayFairKey playFairKey;

	public PlayFair(String key) {

		this.playFairKey = new PlayFairKey(key);
	}

//	public static void main(String[] args) {
//		PlayFair pf = new PlayFair("gravity");
//		System.out.println(pf.encrypt("cryptographyandnetworksecurity").toUpperCase());
//
//	}

	public String encrypt(String plainText) {
		plainText = handelPlainText(plainText);
		String cipherText = "";
		for (int i = 0; i < plainText.length(); i += 2) {
			cipherText += playFairKey.encrypt(plainText.charAt(i), plainText.charAt(i + 1));
		}
		return cipherText;
	}

	private String handelPlainText(String plainText) {
		plainText = plainText.toLowerCase();

		String pt = "";
		for (int i = 0; i < plainText.length(); i++) {
			if(plainText.charAt(i) == ' ')
				continue;
			pt += plainText.charAt(i);
		}
		plainText = pt;

		String str = "";
		boolean odd = (plainText.length() % 2 == 1);
		for (int i = 0; i < plainText.length() - 1; i += 2) {
			if (plainText.charAt(i) != plainText.charAt(i + 1)) {
				str += plainText.charAt(i) + "" + plainText.charAt(i + 1);
			} else {
				odd = !odd;
				str += plainText.charAt(i) + "x";
				i--;
			}
		}
		if (odd)
			str += plainText.charAt(plainText.length() - 1) + "x";
		// System.out.println(str);
		return str;
	}

	public String decrypt(String cipherText) {
		String plainText = "";
		for (int i = 0; i < cipherText.length(); i += 2) {
			plainText += playFairKey.decrypt(cipherText.charAt(i), cipherText.charAt(i + 1));
		}
		plainText = handelX(plainText);
		return plainText;
	}

	private String handelX(String plainText) {
		String newStr = "";
		newStr += plainText.charAt(0);
		for (int i = 1; i < plainText.length() - 1; i++) {
			if (plainText.charAt(i) == 'x' && (plainText.charAt(i - 1) == plainText.charAt(i + 1))) {
				continue;
			} else {
				newStr += plainText.charAt(i);
			}

		}
		if (plainText.charAt(plainText.length() - 1) != 'x') {
			newStr += plainText.charAt(plainText.length() - 1);
		}
		return newStr;
	}

	public File encrypt(File plainTextFile) throws IOException {

		Scanner scanner = new Scanner(plainTextFile);

		File output = new File("encrypt.txt");
		PrintWriter pw = new PrintWriter(output);

		while (scanner.hasNext()) {
			pw.println(encrypt(scanner.nextLine()));
		}

		pw.flush();
		pw.close();
		scanner.close();

		return output;
		// return null;
	}

	public File decrypt(File cipherTextFile) throws IOException {

		Scanner input = new Scanner(cipherTextFile);

		File output = new File("decrypt.txt");
		PrintWriter pw = new PrintWriter(output);

		while (input.hasNext()) {
			pw.println(decrypt(input.nextLine()));
		}

		pw.flush();
		pw.close();
		input.close();
		return output;
		// return null;
	}

	/**
	 * Playfair key class that manage the initialization of the key matrix and the
	 * encryption/decryption of two letters.
	 */
	static class PlayFairKey {

		private static final int MATRIX_SIZE = 5;

		private final char[][] keyMatrix = new char[MATRIX_SIZE][MATRIX_SIZE];

		public PlayFairKey(String key) {

			buildKeyMatrix(key);
		}

		/**
		 * Initialization the key matrix.
		 */
		private void buildKeyMatrix(String key) {
			key = cleanKey(key);
			int indexofkey = 0;
			char ch = 'a';
			boolean[] isFound = new boolean[26];
			isFound['j' - 'a'] = true;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (indexofkey < key.length()) {
						keyMatrix[i][j] = key.charAt(indexofkey);
						isFound[key.charAt(indexofkey) - 'a'] = true;
						indexofkey++;
					} else {
						while (isFound[ch - 'a']) {
							ch++;
						}
						keyMatrix[i][j] = ch;
						ch++;
						if (ch == 'j')
							ch++;
					}
				}
			}
			for (int i = 0; i < 5; i++)
				System.out.println(Arrays.toString(keyMatrix[i]));
		}

		private String cleanKey(String key) {
			key = key.toLowerCase().replace('j', 'i');
			boolean[] repeat = new boolean[26];
			String cleanKey = "";
			for (int i = 0; i < key.length(); i++) {
				if (repeat[key.charAt(i) - 'a']) {
					continue;
				}
				repeat[key.charAt(i) - 'a'] = true;
				cleanKey += key.charAt(i);
			}
			// System.out.println(cleanKey);
			return cleanKey;
		}

		public String encrypt(char ch1, char ch2) {
			// Hint:
			// get row position of ch1 (let I1)
			// get column position of ch1 (let J1)
			// get row position of ch2 (let I2)
			// get column position of ch2 (let J2)
			// the result should be keyMatrix[I1][J2] appended with keyMatrix[I2][J1]
			// should handle the letter in the same column or the same row
			int i1 = rowPosition(ch1);
			int j1 = columnPosition(ch1);
			int i2 = rowPosition(ch2);
			int j2 = columnPosition(ch2);
			if (i1 == i2) {
				return keyMatrix[i1][(j1 + 1) % 5] + "" + keyMatrix[i2][(j2 + 1) % 5];
			} else if (j1 == j2) {
				return keyMatrix[(i1 + 1) % 5][j1] + "" + keyMatrix[(i2 + 1) % 5][j2];
			} else {
				return keyMatrix[i1][j2] + "" + keyMatrix[i2][j1];
			}
			// return null;
		}

		public String decrypt(char ch1, char ch2) {
			// the reverse of the encryption mechanism
			int i1 = rowPosition(ch1);
			int j1 = columnPosition(ch1);
			int i2 = rowPosition(ch2);
			int j2 = columnPosition(ch2);
			if (i1 == i2) {
				return (keyMatrix[i1][(j1 - 1 + 5) % 5] + "" + keyMatrix[i2][(j2 - 1 + 5) % 5]);
			} else if (j1 == j2) {
				return (keyMatrix[(i1 - 1 + 5) % 5][j1] + "" + keyMatrix[(i2 - 1 + 5) % 5][j2]);
			} else {
				return (keyMatrix[i1][j2] + "" + keyMatrix[i2][j1]);
			}
			// return null;
		}

		private int rowPosition(char ch) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (keyMatrix[i][j] == ch)
						return i;
				}
			}
			return 0;
		}

		private int columnPosition(char ch) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (keyMatrix[i][j] == ch)
						return j;
				}
			}
			return 0;
		}

	}
}
