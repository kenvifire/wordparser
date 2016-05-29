package com.itluobo.wordtool.web.controller;

import com.itluobo.wordtool.dao.WordDao;
import com.itluobo.wordtool.entity.Word;
import com.itluobo.wordtool.tools.ShanbayClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by hannahzhang on 16/5/29.
 */
public class SaveWordTool {
    public static void main(String[] args) throws Exception{
        WordDao wordDao = new WordDao();
        List<Word> wordList =
                wordDao.getWordList("Superforecasting: The Art and Science of Prediction",0,9);
        Properties props = new Properties();
        props.load(ShanbayClient.class.getResourceAsStream("/config/app.properties"));
        ShanbayClient shanbayClient = new ShanbayClient();
        shanbayClient.login(props.getProperty("username"), props.getProperty("password"));
        for (Word word : wordList) {
            Thread.sleep(300);
            shanbayClient.addWord(word.getWord(), 307015);
        }


    }
}
