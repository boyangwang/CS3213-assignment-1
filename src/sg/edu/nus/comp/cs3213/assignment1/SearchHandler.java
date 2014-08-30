package sg.edu.nus.comp.cs3213.assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SearchHandler implements ISearchHandler {

	ArrayList<String> searchList = new ArrayList<String>();
	BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));
	EntryProcessor proc = EntryProcessor.getInstance();
	
	
	public String handleSearch() {
		String[] words = new String[0];
		String output = "";
		try {
			words = reader.readLine().split(" ");
		} catch (IOException e) {
			e.printStackTrace();
			return KeyWordInContext.IOEXCEPTION_MSG;
		}
		searchList.clear();
		for (String w : words) {
			searchList.add(w);
		}
		ArrayList<String> results = proc.searchEntriesByKeyWords(searchList);
		for (String result : results) {
			output += result + System.lineSeparator();
		}
		return output;
	}

}
