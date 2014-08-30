package sg.edu.nus.comp.cs3213.assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AddItemHandler implements IAddItemHandler {
	ArrayList<String> ignoreList = new ArrayList<String>();
	ArrayList<String> entryList = new ArrayList<String>();
	BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));
	EntryProcessor proc = EntryProcessor.getInstance();
	
	@Override
	public String handleAddEntriesFile() {
		String output;
		String entryPath;
		try {
			entryPath = reader.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
			return KeyWordInContext.IOEXCEPTION_MSG;
		}
		entryList.clear();
		try {
			IOHandler.readFileToList(entryList, entryPath, KeyWordInContext.READ_FILE_TYPE_ENTRY);
			int resultCount = proc.addEntries(entryList);
			output = String.format(KeyWordInContext.SUCCESS_ADD_MSG, resultCount);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			output = KeyWordInContext.FILE_NOT_FOUND_MSG;
		} catch (IOException e) {
			e.printStackTrace();
			output = KeyWordInContext.IOEXCEPTION_MSG;
		}
		return output;
	}

	@Override
	public String handleAddEntriesManually() {
				
		String line = "";
		entryList.clear();
		try {
			while ((line = KeyWordInContext.reader.readLine()) != null && !line.equals("")) {
				System.out.println("Manual import, input: " + line);
				entryList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return KeyWordInContext.IOEXCEPTION_MSG;
		}

		int resultCount = proc.addEntries(entryList);
		return String.format(KeyWordInContext.SUCCESS_ADD_MSG, resultCount);
	}
	
	
}
