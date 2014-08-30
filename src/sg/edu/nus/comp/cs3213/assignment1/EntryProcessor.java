package sg.edu.nus.comp.cs3213.assignment1;

import java.util.*;

public class EntryProcessor {
	private EntryManager entryMan;
	private static EntryProcessor instance = null;

	private EntryProcessor() {
		init();
	}

	public static EntryProcessor getInstance() {
		if (instance == null) {
			instance = new EntryProcessor();
		}
		return instance;
	}

	public void init() {
		entryMan = new EntryManager();
	}

	public int addEntries(ArrayList<String> entries) {
		try {
			int size = entries.size();
			int success = 0;
			for (int i = 0; i < size; i++) {
				if (entryMan.addEntry(entries.get(i))) {
					success++;
				}
			}
			return success;
		} catch (Exception e) {
			return 0;
		}
	}

	public int addIgnore(ArrayList<String> entries) {
		try {
			int size = entries.size();
			int success = 0;
			for (int i = 0; i < size; i++) {
				if (entryMan.addIgnore(entries.get(i))) {
					success++;
				}
			}
			return success;
		} catch (Exception e) {
			return 0;
		}
	}

	public ArrayList<String> searchEntriesByKeyWords(ArrayList<String> keyWords) {
		try {
			ArrayList<String> result;
			if (keyWords.size() > 0) {
				result = entryMan.getEntriesByKeyWords(keyWords);
			} else {
				result = entryMan.getAllEntries();
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			ArrayList<String> err = new ArrayList<String>();
			err.add(EntryManager.INTERNAL_ERROR_MSG);
			return err;
		}
	}
}
