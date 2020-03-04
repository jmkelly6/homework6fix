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
        String[] splitPhrase = keyPhrase.split(" ");
        List<Integer> locations = new ArrayList<>();

        for(int i = 0; i < splitPhrase.length-1; i++) {
            if(indexes.containsKey(splitPhrase[i]) && indexes.containsKey(splitPhrase[i+1])) {
                for(int j = 0; j < indexes.get(splitPhrase[i]).size(); j++) {
                    if(indexes.get(splitPhrase[i]).size() > 0 && indexes.get(splitPhrase[i+1]).size() > 0) {
                        for(int k = 0; k < indexes.get(splitPhrase[i]).get(j).size(); k++) {
                            if(indexes.get(splitPhrase[i]).get(j).get(k)+1 == indexes.get(splitPhrase[i]).get(j).get(k)) {
                                locations.add(j);
                            }
                        }
                    }
                }
            } else {
                locations.add(-1);
            }
        }

        //for(int i = 0; i < indexes.get(keyPhrase).size(); i++) {
            //if (indexes.get(keyPhrase).get(i).size() > 0)
                //locations.add(i);
        //}
        //for(int j = 0; j < splitPhrase.length; j++) {
            //if(locations.get(j) == ) {
                //for (int doc = 0; doc < splitPhrase.length; doc++) {
                    //locations.set(doc, locations.get(doc) - doc);
                //}
            //}

        return locations;
    }
}
