package sg.edu.nus.comp.cs3213.assignment1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KeyWordInContext {

	private static final String MENU_INSTRUCTIONS = new StringBuilder(
			"Welcome to KWIC! Choose your command followed by arguments. Please see command list and examples below.\n")
			.append("\n")
			.append("1. Add entries via text file e.g. 1 c:\\path\\to\\your\\file \n")
			.append("2. Add entries by manual input, separated by new lines and end by 2 consecutive new lines e.g. 2 aaa \n")
			.append("bbb \n")
			.append("ccc \n")
			.append("\n")
			.append("\n")
			.append("3. Search by keyword, for multiple keywords separate by space e.g. 3 apple banana \n")
			.append("4. Add ignore words via text file e.g. 4 c:\\path\\to\\your\\file \n")
			.append("5. Add entries by manual input, separated by space e.g. 5 orange strawberry \n")
			.append("6. Exit e.g. 6\n")
			.toString();

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		Boolean isExit = false;

		while (!isExit) {
			System.out.println(MENU_INSTRUCTIONS);
			String input = reader.readLine();
			String output = processInput(input, isExit);
			System.out.println(output);
		}

	}

	private static String processInput(String input, Boolean isExit) {
		
		return null;
	}

}
