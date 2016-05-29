package com.itluobo.wordtool.service.impl;

import com.itluobo.wordtool.entity.Word;
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

    private List<Word> wordList = new ArrayList<Word>();

    public void addWord(String word, int chapter) {
        if(!isWord(word)) return;
        Word chapWord = new Word(word);
        chapWord.setChapter(chapter);
        if(!wordList.contains(word)) {
            wordList.add(chapWord);
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


    public List<Word> getWordList() {
        return this.wordList;
    }
    public List<Word> getSortedWords() {
        Collections.sort(wordList, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord().toLowerCase().compareTo(o2.getWord().toLowerCase());
            }
        });
        return wordList;
    }
}
