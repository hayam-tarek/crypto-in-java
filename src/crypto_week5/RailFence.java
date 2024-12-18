package crypto_week5;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class RailFenceBasic {

	private final int depth;

	RailFenceBasic(int depth) {
		this.depth = depth;
	}

	public String encrypt(String plainText) {
		String cipherText = "";
		boolean flag = false;
		int j = 0;
		int row = depth;
		int col = plainText.length();
		char[][] mat = new char[row][col];

		for (int i = 0; i < col; i++) {
			if (j == 0 || j == depth - 1)
				flag = !flag;
			mat[j][i] = plainText.charAt(i);
			if (flag)
				j++;
			else
				j--;
		}
//		for (int m = 0; m < mat.length; m++)
//			System.out.println(Arrays.toString(mat[m]));
		for (int i = 0; i < row; i++) {
			for (int k = 0; k < col; k++) {
				if (mat[i][k] != 0)
					cipherText += mat[i][k];
			}
		}
		return cipherText;
	}

	public String decrypt(String cipherText) {
		String plainText = "";
		boolean flag = false;
		int j = 0;
		int row = depth;
		int col = cipherText.length();
		char[][] mat = new char[row][col];

		for (int i = 0; i < col; i++) {
			if (j == 0 || j == depth - 1)
				flag = !flag;
			mat[j][i] = 'a';
			if (flag)
				j++;
			else
				j--;
		}
		int idx = 0;
		flag = false;
		for (int i = 0; i < row; i++) {
			for (int k = 0; k < col; k++) {
				if (mat[i][k] == 'a' && idx < col) {
					mat[i][k] = cipherText.charAt(idx++);
				}
			}
		}

		j = 0;
		for (int i = 0; i < col; i++) {
			if (j == 0 || j == depth - 1)
				flag = !flag;
			plainText += mat[j][i];
			if (flag)
				j++;
			else
				j--;
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