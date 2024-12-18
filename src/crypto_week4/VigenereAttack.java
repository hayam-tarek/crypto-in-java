package crypto_week4;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class VigenereAttack {

	public static void main(String[] args) throws IOException {

		String key1 = attack("crypto graphy and network security", "IIYKBHEXRPCGTLJEEOEHPQJEXCKGZP"); // key is gravity
		System.out.println("key: " + key1);
		
		String key2 = attack("java is programming", "qeglwztczuyexxwuk"); // key is hello
		System.out.println("key: " + key2);
		
		File pt = new File("PT_Vigenere.txt");
		File ct = new File("CT_Vigenere.txt");
		attack(pt, ct);

	}
	
	public static String attack(String pt, String ct) {
		pt = pt.toUpperCase();
		ct = ct.toUpperCase();
		
		String key = "";
		pt = handlePT(pt);
		for (int i = 0; i < pt.length(); i++) {
			char p = pt.charAt(i);
			char c = ct.charAt(i);
			key += (char) ((((c - 'A') - (p - 'A')) + 26) % 26 + 'A');
		}
		return key.toLowerCase();
	}

	public static String handlePT(String pt) { //to remove the spaces
		String res = "";
		for (int i = 0; i < pt.length(); i++) {
			char c = pt.charAt(i);
			if (c < 'A' || c > 'Z')
				continue;
			res += pt.charAt(i);
		}
		return res;
	}
	public static void attack(File plainTextFile, File cipherTextFile) throws IOException {
		Scanner readPT = new Scanner(plainTextFile);
		Scanner readCT = new Scanner(cipherTextFile);

		File output = new File("output.txt");
		PrintWriter write = new PrintWriter(output);

		while (readPT.hasNext() && readPT.hasNext()) {
			write.println(attack(readPT.nextLine(), readCT.nextLine()));
		}

		write.flush();
		write.close();
		readPT.close();
		readCT.close();

		//return output;
	}

}
