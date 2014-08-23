package sg.edu.nus.comp.cs3213.assignment1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class KeyWordInContext {

	private static final int READ_FILE_TYPE_IGNORE = 1;
	private static final int READ_FILE_TYPE_ENTRY = 0;
	
	private static final int COMMAND_NUMBER_ADD_ENTRIES_MAN = 2;
	private static final int COMMAND_NUMBER_ADD_ENTRIES_FILE = 1;
	private static final int COMMAND_NUMBER_SEARCH = 5;
	private static final int COMMAND_NUMBER_ADD_IGNORE_FILE = 3;
	private static final int COMMAND_NUMBER_ADD_IGNORE_MAN = 4;
	private static final int COMMAND_NUMBER_EXIT = 6;
	
	private static final String INVALID_COMMAND_MSG = "Invalid command. Your input is: %s\nCheck your input.";
	private static final String EXIT_MSG = "Bye";
	
	private static final String MENU_INSTRUCTIONS = new StringBuilder(
			"Welcome to KWIC! Choose your command followed by arguments. Please see command list and examples below.\n")
			.append("\n")
			.append("1. Add entries via text file e.g. 1 c:\\path\\to\\your\\file \n")
			.append("2. Add entries by manual input, separated by new lines and end by 2 consecutive new lines e.g. 2 aaa \n")
			.append("bbb \n")
			.append("ccc \n")
			.append("\n")
			.append("\n")
			.append("3. Add ignore words via text file e.g. 4 c:\\path\\to\\your\\file \n")
			.append("4. Add ignore words by manual input, separated by space e.g. 5 orange strawberry \n")
			.append("5. Search by keyword, for multiple keywords separate by space e.g. 3 apple banana \n")
			.append("6. Exit e.g. 6\n")
			.append("\n")
			.toString();
	
	private static Boolean isExit = false;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		while (!isExit ) {
			System.out.println(MENU_INSTRUCTIONS);
			String input = reader.readLine();
			String output = 
					processInput(input);
			System.out.println(output);
		}

	}

	private static String processInput(String input) {
		input = input.trim();
		
		String output = String.format(INVALID_COMMAND_MSG, input);
		int command = Integer.parseInt(input.substring(0, 1));
		switch(command) {
		case COMMAND_NUMBER_ADD_ENTRIES_FILE: 
			String entryPath = input.substring(2);
			ArrayList<String> entryList = readFileToList(entryPath, READ_FILE_TYPE_ENTRY);
			break;
		case COMMAND_NUMBER_ADD_ENTRIES_MAN: 
			break;
		case COMMAND_NUMBER_SEARCH: 
			break;
		case COMMAND_NUMBER_ADD_IGNORE_FILE:
			String ignorePath = input.substring(2);
			ArrayList<String> ignoreList = readFileToList(ignorePath, READ_FILE_TYPE_IGNORE);
			break;
		case COMMAND_NUMBER_ADD_IGNORE_MAN: 
			break;
		case COMMAND_NUMBER_EXIT:
			isExit = true;
			output = EXIT_MSG;
			break;
		default: 
		}
		
		return output;
	}

	private static ArrayList<String> readFileToList(String path,
			int readFileType) {
		
		return null;
	}

}
