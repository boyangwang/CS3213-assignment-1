package sg.edu.nus.comp.cs3213.assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyWordInContext {

	static final int READ_FILE_TYPE_IGNORE = 1;
	static final int READ_FILE_TYPE_ENTRY = 0;

	public static final int COMMAND_NUMBER_ADD_ENTRIES_MAN = 2;
	public static final int COMMAND_NUMBER_ADD_ENTRIES_FILE = 1;
	public static final int COMMAND_NUMBER_SEARCH = 5;
	public static final int COMMAND_NUMBER_ADD_IGNORE_FILE = 3;
	public static final int COMMAND_NUMBER_ADD_IGNORE_MAN = 4;
	public static final int COMMAND_NUMBER_EXIT = 6;

	static final String INVALID_COMMAND_MSG = "Invalid command. Your input is: %s\nCheck your input.";
	static final String IOEXCEPTION_MSG = "IO exception has occurred. Check file system permission and buffer size.";
	static final String EXIT_MSG = "Bye";
	static final String SUCCESS_MSG = "Successful!\n";
	static final String FILE_NOT_FOUND_MSG = "The file you specified is not found";

	private static final String MENU_INSTRUCTIONS = new StringBuilder(
			"Welcome to KWIC! Input your command number and then enter. Then input command argument(s). Please see command list and examples below.")
			.append("\n")
			.append("1. Add entries via text file e.g. c:\\path\\to\\your\\file\n")
			.append("2. Add entries by manual input, separated by new lines and end by 2 consecutive new lines e.g.\n")
			.append("aaa\n")
			.append("bbb\n")
			.append("ccc\n")
			.append("\n")
			.append("3. Add ignore words via text file e.g. c:\\path\\to\\your\\file\n")
			.append("4. Add ignore words by manual input, separated by space e.g. orange strawberry\n")
			.append("5. Search by keyword, for multiple keywords separate by space e.g. apple banana\n")
			.append("6. Exit e.g. 6\n").append("\n")
			.append("For example, to add 2 entries from manual input:\n")
			.append("2\n").append("The Day after Tomorrow\n")
			.append("Titanic\n").append("\n")
			.append("To add ignore words from file:\n").append("3\n")
			.append("c:\\path\\to\\your\\file\\n").append("\n").append("\n")
			.toString();
	
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));
	static Boolean isExit = false;
	

	public static void main(String[] args) throws Exception {

		while (!isExit) {
			System.out.println(MENU_INSTRUCTIONS);
			String input = reader.readLine();
			String output = processCommandInput(input);
			System.out.println(output);
		}

	}

	private static String processCommandInput(String input) throws IOException {
		int command = Integer.parseInt(input.trim().substring(0, 1));
		String output = "";
		ISearchHandler searchHandler;
		IAddItemHandler addItemHandler;
		IAddIgnoreHandler addIgnoreHandler;
		
		switch (command) {
		case COMMAND_NUMBER_ADD_IGNORE_MAN:
			addIgnoreHandler = new AddIgnoreHandler();
			output = addIgnoreHandler.handleAddIgnoreManually();
			break;
		
		case COMMAND_NUMBER_ADD_ENTRIES_MAN:
			addItemHandler = new AddItemHandler();
			output = addItemHandler.handleAddEntriesManually();
			break;
					
		case COMMAND_NUMBER_ADD_ENTRIES_FILE:
			addItemHandler = new AddItemHandler();
			output = addItemHandler.handleAddEntriesFile();
			break;
			
		case COMMAND_NUMBER_ADD_IGNORE_FILE:
			addIgnoreHandler = new AddIgnoreHandler();
			output = addIgnoreHandler.handleAddIgnoreFile();
			break;
			
		case COMMAND_NUMBER_SEARCH:
			searchHandler = new SearchHandler();
			output = searchHandler.handleSearch();
			break;
			
		case COMMAND_NUMBER_EXIT:
			isExit = true;
			output = EXIT_MSG;
			break;
			
		default:
			output = String.format(INVALID_COMMAND_MSG, input);
		}

		return output;
	}

}
