package sg.edu.nus.comp.cs3213.assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class KeyWordInContext {

	private static final String FILE_NOT_FOUND_MSG = "The file you specified is not found";
	private static final int READ_FILE_TYPE_IGNORE = 1;
	private static final int READ_FILE_TYPE_ENTRY = 0;

	private static final int COMMAND_NUMBER_ADD_ENTRIES_MAN = 2;
	private static final int COMMAND_NUMBER_ADD_ENTRIES_FILE = 1;
	private static final int COMMAND_NUMBER_SEARCH = 5;
	private static final int COMMAND_NUMBER_ADD_IGNORE_FILE = 3;
	private static final int COMMAND_NUMBER_ADD_IGNORE_MAN = 4;
	private static final int COMMAND_NUMBER_EXIT = 6;

	private static final String INVALID_COMMAND_MSG = "Invalid command. Your input is: %s\nCheck your input.";
	private static final String IOEXCETION_MSG = "IO exception has occurred. Check file system permission and buffer size.";
	private static final String EXIT_MSG = "Bye";

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

	private static Boolean isExit = false;
	private static ArrayList<String> ignoreList = new ArrayList<String>();
	private static ArrayList<String> entryList = new ArrayList<String>();
	private static ArrayList<String> searchList = new ArrayList<String>();
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));

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
		EntryProcessor proc = EntryProcessor.getInstance();
		String output = "";
		String[] words;
		
		switch (command) {
		case COMMAND_NUMBER_ADD_IGNORE_MAN:
			words = reader.readLine().split(" ");
			ignoreList.clear();
			for (String w : words) {
				ignoreList.add(w);
			}
			proc.addIgnore(ignoreList);
			break;
		
		case COMMAND_NUMBER_ADD_ENTRIES_MAN:
			String line = "";
			entryList.clear();
			while ((line = reader.readLine()) != null && !line.equals(System.lineSeparator())) {
				entryList.add(line);
			}
			proc.addEntries(entryList);
			break;
					
		case COMMAND_NUMBER_ADD_ENTRIES_FILE:
			String entryPath = reader.readLine();
			entryList.clear();
			try {
				readFileToList(entryPath, READ_FILE_TYPE_ENTRY);
				proc.addEntries(entryList);
				output = "";
			} catch (FileNotFoundException e) {
				output = FILE_NOT_FOUND_MSG;
			} catch (IOException e) {
				output = IOEXCETION_MSG;
			}
			break;
			
		case COMMAND_NUMBER_ADD_IGNORE_FILE:
			String ignorePath = reader.readLine();
			ignoreList.clear();
			try {
				readFileToList(ignorePath, READ_FILE_TYPE_IGNORE);
				proc.addIgnore(ignoreList);
				output = "";
			} catch (FileNotFoundException e) {
				output = FILE_NOT_FOUND_MSG;
			} catch (IOException e) {
				output = IOEXCETION_MSG;
			}
			break;
			
		case COMMAND_NUMBER_SEARCH:
			words = reader.readLine().split(" ");
			searchList.clear();
			for (String w : words) {
				searchList.add(w);
			}
			proc.searchEntriesByKeyWords(searchList);
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

	@SuppressWarnings("unchecked")
	private static void readFileToList(String path,
			int readFileType) throws IOException, FileNotFoundException {
		File f = new File(path);
		BufferedReader r = new BufferedReader(new FileReader(f));
		if (readFileType == READ_FILE_TYPE_ENTRY) {
			String line = null;
			while ((line = r.readLine()) != null) {
				entryList.add(line);
			}
			
		} else {
			String line = null;
			while ((line = r.readLine()) != null) {
				String[] words = line.split(" ");
				for (String w : words) {
					ignoreList.add(w);
				}
			}
			
		}

	}

}
