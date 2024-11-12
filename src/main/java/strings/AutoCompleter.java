package strings;


/**
 * The AutoCompleter class implements a trie data structure to provide auto-completion functionality.
 * It uses a Node class to represent each node in the trie, where each node can have up to 26 children
 * (one for each lowercase letter). The trie is constructed from a given dictionary of words,
 * and the autoComplete method can be used to find the first word in the dictionary that starts with a given prefix.
 *
 * Example:
 *     // Initialize AutoCompleter with a dictionary
 *     String[] dictionary = {"cat", "car", "dog", "door"};
 *     AutoCompleter completer = new AutoCompleter(dictionary);
 *
 *     // Auto-complete examples
 *     System.out.println(completer.complete("ca")); // Output: "cat"
 *     System.out.println(completer.complete("do")); // Output: "dog"
 */
public class AutoCompleter {
    private static final int R = 26;
    private Node root = new Node();

    /**
     * Represents a single node in the trie. Each node contains an array of child nodes
     * (one for each letter of the alphabet) and a flag is_key indicating if it represents the end of a word.
     */
    private static class Node {
        boolean is_key = false;
        private Node[] next = new Node[R];
    }

    /**
     * Converts a lowercase letter to an index in the range 0-25.
     *
     * @param c the character to convert
     * @return the corresponding index
     */
    private static int charToIndex(char c) {
        return ((int) c) - 97;
    }

    /**
     * Converts an index in the range 0-25 to a lowercase letter.
     *
     * @param i the index to convert
     * @return the corresponding character
     */
    private static char indexToChar(int i) {
        return (char) (i + 97);
    }

    public AutoCompleter(String[] dictionary) {
        for (String word : dictionary) {
            this.insert(word);
        }
    }

    /**
     * Inserts a word into the trie by calling a recursive helper method.
     *
     * @param word the word to insert into the trie
     */
    private void insert(String word) {
        this.root = insert(root, word, 0);
    }

    /**
     * Recursive helper method that inserts characters of a word into the trie.
     *
     * @param node the current node in the trie
     * @param word the word to insert
     * @param d    the current depth (or index) in the word being processed
     * @return the updated node after insertion
     */
    private Node insert(Node node, String word, int d) {
        if (node == null) {
            node = new Node();
        }
        if (d == word.length()) {
            node.is_key = true;
            return node;
        }
        char c = word.charAt(d);
        node.next[charToIndex(c)] = insert(node.next[charToIndex(c)], word, d+1);
        return node;
    }


    /**
     * Returns the first word in the dictionary that starts with the given prefix.
     *
     * @param prefix the prefix to search for
     * @return the first word in the dictionary that starts with the prefix, or null if no such word is found
     */
    public String complete(String prefix) {
        // TODO
         return null;
    }
}
