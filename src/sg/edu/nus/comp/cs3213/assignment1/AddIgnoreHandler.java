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
//		System.out.println("length of ignorelist: " + ignoreList.size());
//		for (String word : ignoreList) {
//			System.out.println("ignore word: " + word);
//		}
		int resultCount = proc.addIgnore(ignoreList);
		return String.format(KeyWordInContext.SUCCESS_ADD_MSG, resultCount);
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
			FileInputHandler.readFileToList(ignoreList, ignorePath, KeyWordInContext.READ_FILE_TYPE_IGNORE);
//			System.out.println("length of ignorelist: " + ignoreList.size());
			int resultCount = proc.addIgnore(ignoreList);
			output = String.format(KeyWordInContext.SUCCESS_ADD_MSG, resultCount);
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
