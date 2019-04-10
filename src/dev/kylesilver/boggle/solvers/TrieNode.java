package dev.kylesilver.boggle.solvers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode {

    private char index;
    private boolean isTerminator;
    private String word;
    private Map<Character, TrieNode> children;

    private TrieNode(char index) {
        this.index = index;
        isTerminator = false;
        word = "";
        children = new HashMap<>();
    }

    public static TrieNode buildTrie(List<String> words) {
        TrieNode root = new TrieNode('\0');
        words.forEach(word -> addWord(word, root));
        return root;
    }

    private static void addWord(String word, TrieNode root) {
        TrieNode ptr = root;
        for (char c : word.toCharArray()) {
            ptr.addChild(c);
            ptr = ptr.getChild(c);
        }
        ptr.setIsTerminator(true);
        ptr.setWord(word);
    }

    public TrieNode getChild(char key) {
        return children.getOrDefault(key, null);
    }

    public void addChild(char key) {
        if (!children.containsKey(key)) {
            children.put(key, new TrieNode(key));
        }
    }

    public boolean containsKey(char key) {
        return children.containsKey(key);
    }

    public void setIsTerminator(boolean isTerminator) {
        this.isTerminator = isTerminator;
    }

    public boolean isTerminator() {
        return isTerminator;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public char getIndex() {
        return index;
    }

}
