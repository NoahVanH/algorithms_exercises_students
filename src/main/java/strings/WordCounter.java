package strings;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implement the class WordCounter that counts the number of occurrences
 * of each word in a given piece of text.
 * Feel free to use existing java classes.
 */
public class WordCounter implements Iterable<String> {
    /*
    * Key: le mot
    * Value: nb occurence*/

    public Hashtable<String, Integer> HT;
    public WordCounter() {
        HT = new Hashtable<String, Integer>();

    }

    /**
     * Add the word so that the counter of the word is increased by 1
     */
    public void addWord(String word) {
        if(HT.containsKey(word)){
            // +1
            int value = HT.get(word);
            HT.put(word,value+1);

        } else {
            HT.put(word,1);

        }

    }

    /**
     * Return the number of times the word has been added so far
     */
    public int getCount(String word) {
        if(!HT.containsKey(word)){
            return 0;
        }
         return HT.get(word);
    }

    // iterate over the words in ascending lexicographical order
    @Override
    public Iterator<String> iterator() {
        // TreeMap pour avoir le tri d'un RED Black
        TreeMap<String, Integer> sortedMap = new TreeMap<>(HT);
        return sortedMap.keySet().iterator();
    }
}
