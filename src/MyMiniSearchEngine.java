import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMiniSearchEngine {
    // default solution. OK to change.
    // do not change the signature of index()
    private Map<String, List<List<Integer>>> indexes;

    // disable default constructor
    private MyMiniSearchEngine() {
    }

    public MyMiniSearchEngine(List<String> documents) {
        index(documents);
    }

    // each item in the List is considered a document.
    // assume documents only contain alphabetical words separated by white spaces.
    private void index(List<String> texts) {
        indexes = new HashMap<>();
        for(int doc = 0; doc < texts.size(); doc++) {
            String[] tokens = texts.get(doc).split(" ");
            for(int wordKey = 0; wordKey < tokens.length; wordKey++) {
                List<List<Integer>> currentWordLocations = new ArrayList<>();
                for(int i = 0; i < texts.size(); i++) {
                    currentWordLocations.add(new ArrayList<>());
                }
                if(!indexes.containsKey(tokens[wordKey])){
                    indexes.put(tokens[wordKey].toLowerCase(), currentWordLocations);
                }
                indexes.get(tokens[wordKey]).get(doc).add(wordKey);
            }
        }
    }

    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    public List<Integer> search(String keyPhrase) {
        // homework
        String[] splitPhrase = keyPhrase.toLowerCase().split(" ");
        List<Integer> locations = new ArrayList<>();

        for(int i = 0; i < splitPhrase.length-1; i++) {
            if(indexes.containsKey(splitPhrase[i]) && indexes.containsKey(splitPhrase[i+1])) {
                for(int j = 0; j < indexes.get(splitPhrase[i]).size(); j++) {
                    if(indexes.get(splitPhrase[i]).get(j).size() > 0 && indexes.get(splitPhrase[i+1]).get(j).size() > 0) {
                        for(int k = 0; k < indexes.get(splitPhrase[i]).get(j).size(); k++) {
                            if(indexes.get(splitPhrase[i]).get(j).get(k)+1 == indexes.get(splitPhrase[i+1]).get(j).get(k)) {
                                if(!locations.contains(j)) {
                                    locations.add(j);
                                }
                            }
                        }
                    }
                }
            } else {
                locations.add(-1);
                break;
            }
        }
        if(splitPhrase.length == 1) {
            if (indexes.containsKey(splitPhrase[0])) {
                for (int doc = 0; doc < indexes.get(splitPhrase[0]).size(); doc++) {
                    if (indexes.get(splitPhrase[0]).get(doc).size() > 0) {
                        locations.add(doc);
                    }
                }
            } else {
                locations.add(-1);
            }
        }

        return locations;
    }
}
