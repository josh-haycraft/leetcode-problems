package com.patterns.trie;

class Solution208 {

    public static void main(String[] args) {
        
        // see com.patterns.trie.Trie for class definition
        Trie trie = new Trie();

        trie.insert("banana");

        System.out.println(
            trie.search("banana")   // true
        );
        System.out.println(
            trie.search("ban")   // false
        );
        System.out.println(
            trie.startsWith("ban") // true
        );

        trie.insert("ban");

        System.out.println(
            trie.search("ban")   // true
        );
    }
}