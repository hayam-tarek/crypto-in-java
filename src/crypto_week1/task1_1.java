package crypto_week1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//print in file initial of names
public class task1_1 {
	public static void main(String[] args) throws IOException {
		try {
			File FileObj = new File("names.txt");
			Scanner ReadFile = new Scanner(FileObj);
			FileWriter WriteFile = new FileWriter("initials.txt");
			while (ReadFile.hasNextLine()) {
				String data = ReadFile.nextLine();
				String newData = "";
				newData += data.charAt(0);
				for (int i = 1; i < data.length() - 1; i++) {
					char c = data.charAt(i);
					char cNext = data.charAt(i + 1);
					if (Character.isWhitespace(c) && Character.isLetter(cNext)) {
						newData += cNext;
					}
				}
				WriteFile.write(newData + "\n");
			}
			ReadFile.close();
			WriteFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			System.out.println("Can not find the file.");
			e.printStackTrace();
		}
	}
}