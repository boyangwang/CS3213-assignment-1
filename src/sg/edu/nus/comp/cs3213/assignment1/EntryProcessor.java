package sg.edu.nus.comp.cs3213.assignment1;

import java.util.*;

public class EntryProcessor{
	private TreeMap<String, TreeSet<String>> entryList;
	private HashSet<String> ignoreList;
	private static EntryProcessor instance = null;
	
	public static void main(){
		EntryProcessor ep = EntryProcessor.getInstance();
		ArrayList<String> str = new ArrayList<String>();
		ArrayList<String> ignore = new ArrayList<String>();
		str.add("The Day After Tomorrow");
		str.add("Fast and Furious");
		str.add("Man of Steel");
		ep.addEntries(str);
		
		ignore.add("is");
		ignore.add("of");
		ignore.add("the");
		ignore.add("and");
		ignore.add("as");
		ignore.add("a");
		ignore.add("after");
		ep.addIgnore(ignore);
		
	}

	private EntryProcessor(){	
		init();
	}
	
	public static EntryProcessor getInstance(){
		if(instance == null){
			instance = new EntryProcessor();
		}
		return instance;
	}

	public void init(){
		entryList = new TreeMap<String, TreeSet<String>>();
		ignoreList = new HashSet<String>();
	}

	public boolean addEntries(ArrayList<String> entries){
		int size = entries.size();
		for(int i=0; i<size; i++){
			String entry = entries.get(i);
			String[] words = entry.split(" ");
			int noOfWords = words.length;
			for(int k=0; k<noOfWords; k++){
				if(ignoreList.contains(words[k])==false){
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
		}

		return true;
	}

	public boolean addIgnore(ArrayList<String> entries){
		int size = entries.size();
		for(int i=0; i<size; i++){
			ignoreList.add(entries.get(i));
		}
		return true;
	}

	public ArrayList<String> searchEntriesByKeyWords(ArrayList<String> keyWords){
		ArrayList<String> entries = new ArrayList<String>();
		int size = keyWords.size();
		Collections.sort(keyWords);
		for(int i=0; i<size; i++){
			TreeSet<String> strings = entryList.get(keyWords.get(i));
			Iterator it = strings.iterator();
			while(it.hasNext()){
				entries.add(it.next().toString());
			}
		}
		return entries;
	}
}
