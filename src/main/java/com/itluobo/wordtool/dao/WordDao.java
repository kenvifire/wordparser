package com.itluobo.wordtool.dao;

import com.itluobo.wordtool.entity.Word;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kenvi on 16/2/16.
 */
@Component
public class WordDao extends BasicDao{

    public WordDao() {
        super(DB.getConnection());
    }

    public WordDao(Connection connection) {
        super(connection);
    }

    public int addWord(Word word) {

        if(countWord(word) > 0) {
            return 0;
        }
        try {
            PreparedStatement pstmt = connection.prepareStatement("insert into words(word,is_familiar,book,chapter) values(?,0,?,?);");
            pstmt.setString(1, word.getWord());
            pstmt.setString(2, word.getBook());
            pstmt.setInt(3, word.getChapter());
            int result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int updateWord(Word word) {
        try{
            PreparedStatement pstmt = connection.prepareStatement("update words set is_familiar=?,shanbayId=? where id = ?");
            pstmt.setInt(1, word.isFamiliar() ? 1 : 0);
            pstmt.setString(2, word.getShanbayId());
            pstmt.setInt(3, word.getId());
            int result = pstmt.executeUpdate();
            pstmt.close();
            return result;

        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int countFamiliar(String word) {
        try{
            PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(1) FROM words where word = ? and is_familiar=1 ");
            pstmt.setString(1, word);
            ResultSet rs = pstmt.executeQuery();
            int result = 0;
            if(rs.next()) {
                result = rs.getInt(1);
            }
            pstmt.close();
            return result;

        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int countWord(Word word) {
        try{
            PreparedStatement pstmt = connection.prepareStatement("SELECT count(1) from words where word=?");
            pstmt.setString(1, word.getWord());
            ResultSet rs = pstmt.executeQuery();
            int result = 0;
            if(rs.next()) {
                result = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            return result;

        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Word> getWordList(String book, int beginChapter, int endChapter) {

        try{
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM WORDS WHERE IS_FAMILIAR=0 AND BOOK=? AND chapter>=? AND chapter<=?");
            pstmt.setString(1, book);
            pstmt.setInt(2, beginChapter);
            pstmt.setInt(3, endChapter);
            ResultSet rs = pstmt.executeQuery();
            List<Word> result = new ArrayList<Word>();
            while(rs.next()) {
                Word word = new Word(rs.getString("word"));
                word.setBook(rs.getString("book"));
                word.setId(rs.getInt("id"));
                word.setFamiliar(rs.getInt("is_familiar") == 1);
                word.setShanbayId(rs.getString("shanbayId"));
                word.setChapter(rs.getInt("chapter"));

                result.add(word);
            }

            rs.close();
            pstmt.close();
            return result;

        }catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }



}
