package com.itluobo.wordtool.service.impl;

import com.itluobo.wordtool.dao.WordDao;
import com.itluobo.wordtool.entity.Book;
import com.itluobo.wordtool.entity.Word;
import com.itluobo.wordtool.service.WordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kenvi on 16/2/16.
 */
@Service
public class WordServiceImpl implements WordService{
    @Resource
    private WordDao wordDao;

    public List<Word> getWordList(String book,int beginChapter, int endChapter) {
        return wordDao.getWordList(book, beginChapter, endChapter);

    }

    public List<Book> getBookList() {
        return wordDao.getBookList();
    }

}
