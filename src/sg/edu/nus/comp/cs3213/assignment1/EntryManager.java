package sg.edu.nus.comp.cs3213.assignment1;

import java.util.*;

public class EntryManager{

	private TreeMap<String, TreeSet<String>> entryList;
	private HashSet<String> ignoreList;
	public EntryManager(){
		init();
	}

	public void init(){
		entryList = new TreeMap<String, TreeSet<String>>();
		ignoreList = new HashSet<String>();
	}
	public boolean addEntry(String entry){
		String[] words = entry.split(" ");
		boolean result = true;
		int noOfWords = words.length;
		try{
			//Make characters of words in ignore list to all lower case
			for(int k=0; k<noOfWords; k++){
				if(ignoreList.contains(words[k].toLowerCase())){
					words[k] = words[k].toLowerCase();
				} else {
					words[k] = words[k].substring(0, 1).toUpperCase().concat(words[k].substring(1)); 
				}
			}

			for(int k=0; k<noOfWords; k++){
				if(ignoreList.contains(words[k].toLowerCase())==false){
					StringBuilder strBuilder = new StringBuilder();
					TreeSet<String> stringBeginWithWord = null;
					for(int l=k; l<noOfWords; l++){
						strBuilder.append(words[l]+" ");
					}
					for(int l=0; l<k; l++){
						strBuilder.append(words[l]+" ");
					}

					if(entryList.containsKey(words[k])){
						stringBeginWithWord = entryList.get(words[k]);
						stringBeginWithWord.add(strBuilder.toString());
					} else {
						stringBeginWithWord = new TreeSet<String>();
						stringBeginWithWord.add(strBuilder.toString());
						entryList.put(words[k], stringBeginWithWord);
					}
				}
			}
		} catch (NullPointerException npe) {
			result = false;
		}

		return result;
	}

	public boolean addIgnore(String entry){
		boolean result = true;
		try{
			ignoreList.add(entry.toLowerCase());
		} catch (NullPointerException npe) {
			result = false;
		}
		return result;
	}

	public ArrayList<String> getEntriesByKeyWords(ArrayList<String> keyWords){
		ArrayList<String> entries = new ArrayList<String>();
		int size = keyWords.size();
		Collections.sort(keyWords);
		for(int i=0; i<size; i++){
			String str = keyWords.get(i);
			TreeSet<String> strings = entryList.get(str.substring(0,1).toUpperCase().concat(str.substring(1)));
			if(strings != null){
				Iterator it = strings.iterator();
				while(it.hasNext()){
					entries.add(it.next().toString());
				}
			}
		}
		return entries;
	}

	public ArrayList<String> getAllEntries(){
		ArrayList<String> entries = new ArrayList<String>();
		Collection mapValues = entryList.values();
		Iterator entryIt = mapValues.iterator();
		for(Map.Entry<String, TreeSet<String>> keyWords : entryList.entrySet()){
			TreeSet<String> strings = keyWords.getValue();
			if(strings != null){
				Iterator it = strings.iterator();
				while(it.hasNext()){
					entries.add(it.next().toString());    
				}
			}
		}

		return entries;
	}
}
