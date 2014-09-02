package sg.edu.nus.comp.cs3213.assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileInputHandler {

	public static void readFileToList(ArrayList<String> list, String path,
			int readFileType) throws IOException, FileNotFoundException {
		File f = new File(path);
		
		BufferedReader r = new BufferedReader(new FileReader(f));
		if (readFileType == KeyWordInContext.READ_FILE_TYPE_ENTRY) {
			String line = null;
			while ((line = r.readLine()) != null) {
				line = line.toLowerCase();
				list.add(line);
			}
			
		} else {
			String line = null;
			while ((line = r.readLine()) != null) {
				String[] words = line.split(" ");
				for (String w : words) {
					list.add(w);
				}
			}
			
		}
		r.close();
	
	}

}
