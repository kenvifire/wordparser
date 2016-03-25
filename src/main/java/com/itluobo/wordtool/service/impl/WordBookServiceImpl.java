package com.itluobo.wordtool.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kenvi on 16/2/15.
 */

@Service
public class WordBookServiceImpl {

    private List<String> wordList = new ArrayList<String>();

    public void addWord(String word) {
        if(!isWord(word)) return;

        if(!wordList.contains(word)) {
            wordList.add(word);
        }
    }

    private boolean isWord(String word) {
        // null
        if(word == null || word.length() == 0)
            return false;

        if(word.charAt(0) == '-') return false;
        for(int i=0; i < word.length() ; i ++ ) {
            char c = word.charAt(i);
            if(!Character.isAlphabetic(c) && !(c=='-')) {
                return false;
            }
        }
        return true;
    }


    public List<String> getWordList() {
        return this.wordList;
    }
    public List<String> getSortedWords() {
        Collections.sort(wordList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.toLowerCase().compareTo(o2.toLowerCase());
            }
        });
        return wordList;
    }
}
