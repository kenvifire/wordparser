package com.itluobo.wordtool.service;

import com.itluobo.wordtool.entity.Book;
import com.itluobo.wordtool.entity.Word;

import java.util.List;

/**
 * Created by kenvi on 16/2/16.
 */
public interface WordService {
    List<Word> getWordList(String book, int beginChapter, int endChapter);

    List<Book> getBookList();
}
