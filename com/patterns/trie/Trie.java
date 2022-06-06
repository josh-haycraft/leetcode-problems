package com.patterns.trie;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children;
    boolean wordEnd;
    
    public TrieNode() {
        children = new HashMap<>();
    }
}

public class Trie {
    
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr =  root;
        for (int i=0; i<word.length(); i++) {
            Character c = word.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            } 
            
            curr = curr.children.get(c);
        }
        
        curr.wordEnd = true;
    }
    
    public boolean search(String word) {
        TrieNode match = findLastMatching(word);
        return (null != match && match.wordEnd);
    }
    
    public boolean startsWith(String prefix) {
        TrieNode match = findLastMatching(prefix);
        return (null != match);
    }
    
    private TrieNode findLastMatching(String word) {
        TrieNode curr = root;
        for (int i=0; i<word.length(); i++) {
            Character c = word.charAt(i);
            if (!curr.children.containsKey(c)) {
                return null;
            } else {
                curr = curr.children.get(c);
            }
        }
        
        return curr;
    }
}