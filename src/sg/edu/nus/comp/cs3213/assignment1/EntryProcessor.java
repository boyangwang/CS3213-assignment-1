import java.util.*;

public class EntryProcessor{
	private EntryManager entryMan;
	private static EntryProcessor instance = null;
	
	
	public static void main(String[] args){
		EntryProcessor ep = EntryProcessor.getInstance();
		ArrayList<String> ignore = new ArrayList<String>();
		ignore.add("is");
		ignore.add("of");
		ignore.add("the");
		ignore.add("and");
		ignore.add("as");
		ignore.add("a");
		ignore.add("after");
		System.out.println("ignorelist size="+ep.addIgnore(ignore));
		
		ArrayList<String> str = new ArrayList<String>();
		str.add("The Day after tomorrow");
		str.add("Fast and Furious");
		str.add("Man of Steel");
		System.out.println("entryList size ="+ep.addEntries(str));

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("Day");
		keywords.add("Fast");
		keywords.add("Furious");
		keywords.add("Man");
		keywords.add("Steel");
		keywords.add("Tomorrow");
		ArrayList<String> result = ep.searchEntriesByKeyWords(keywords);
		for(int i=0; i < result.size(); i++){
			System.out.println(result.get(i));
		}
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
		entryMan = new EntryManager();
	}

	public int addEntries(ArrayList<String> entries){
		int size = entries.size();
		int success = 0;
		for(int i=0; i<size; i++){
			if(entryMan.addEntry(entries.get(i))){
				success++;
			}
		}
		return success;
	}

	public int addIgnore(ArrayList<String> entries){
		int size = entries.size();
		int success = 0;
		for(int i=0; i<size; i++){
			if(entryMan.addIgnore(entries.get(i))){
				success++;
			}
		}
		return success;
	}

	public ArrayList<String> searchEntriesByKeyWords(ArrayList<String> keyWords){
		ArrayList<String> result;
		if(keyWords.size() > 0){
			result = entryMan.getEntriesByKeyWords(keyWords);
		} else {
			result = entryMan.getAllEntries();
		}
		return result;
	}
}
