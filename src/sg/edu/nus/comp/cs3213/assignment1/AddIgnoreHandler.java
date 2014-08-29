package sg.edu.nus.comp.cs3213.assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AddIgnoreHandler implements IAddIgnoreHandler {
	
	ArrayList<String> ignoreList = new ArrayList<String>();
	ArrayList<String> entryList = new ArrayList<String>();
	BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));
	EntryProcessor proc = EntryProcessor.getInstance();

	@Override
	public String handleAddIgnoreManually(){
		
		String[] words;
		try {
			words = reader.readLine().split(" ");
		} catch (IOException e) {
			e.printStackTrace();
			return KeyWordInContext.IOEXCEPTION_MSG;
		}
		ignoreList.clear();
		for (String w : words) {
			ignoreList.add(w);
		}
		proc.addIgnore(ignoreList);
		return KeyWordInContext.SUCCESS_MSG;
	}

	
	@Override
	public String handleAddIgnoreFile() {
		
		String output;
		String ignorePath;
		try {
			ignorePath = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return KeyWordInContext.IOEXCEPTION_MSG;
		}
		ignoreList.clear();
		try {
			IOHandler.readFileToList(ignoreList, ignorePath, KeyWordInContext.READ_FILE_TYPE_IGNORE);
			proc.addIgnore(ignoreList);
			output = "";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return KeyWordInContext.FILE_NOT_FOUND_MSG;
		} catch (IOException e) {
			e.printStackTrace();
			return KeyWordInContext.IOEXCEPTION_MSG;
		}
		return output;
	}

}