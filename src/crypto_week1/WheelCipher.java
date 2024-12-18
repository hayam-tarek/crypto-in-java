package crypto_week1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Wheel Cipher
public class WheelCipher {
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the kay: ");
		char charKey = input.next().charAt(0);
		int key = (charKey - 'a') % 26;
/*
 *		ENCRYPTION
 */
		try {
			File FileObj = new File("plainText.txt");
			Scanner ReadFile = new Scanner(FileObj);
			FileWriter WriteFile = new FileWriter("cipherText.txt");
			while (ReadFile.hasNextLine()) {
				String data = ReadFile.nextLine();
				for (int i = 0; i < data.length(); i++) {
					if (!Character.isLetter(data.charAt(i))) {
						WriteFile.write(data.charAt(i));
						continue;
					}
					int ascChar = data.charAt(i);
					WriteFile.write(Character.toUpperCase((char) ((ascChar + key - 'a') % 26 + 'a')));
				}
				WriteFile.write("\n");
			}
			ReadFile.close();
			WriteFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			System.out.println("Can not find the file.");
			e.printStackTrace();
		}
		System.err.println("Encryption DONE.");
/*
 * 		DECRYPTION
 */
		try {
			File FileObj = new File("cipherText.txt");
			Scanner ReadFile = new Scanner(FileObj);
			FileWriter WriteFile = new FileWriter("outputText.txt");
			while (ReadFile.hasNextLine()) {
				String data = ReadFile.nextLine();
				for (int i = 0; i < data.length(); i++) {
					if (!Character.isLetter(data.charAt(i))) {
						WriteFile.write(data.charAt(i));
						continue;
					}
					int ascChar = data.charAt(i);
					WriteFile.write(Character.toLowerCase((char) ((ascChar - 'A' - key + 26) % 26 + 'A')));
				}
				WriteFile.write("\n");
			}
			ReadFile.close();
			WriteFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			System.out.println("Can not find the file.");
			e.printStackTrace();
		}
		System.err.println("Decryption DONE.");

	}
}
