package com.itluobo.wordtool.entity;

/**
 * Created by kenvi on 16/2/16.
 */
public class Word {
    private int id;
    private String word;
    private boolean isFamiliar;
    private String shanbayId;
    private String book;

    public Word(String word) {
        this.word = word.toLowerCase();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isFamiliar() {
        return isFamiliar;
    }

    public void setFamiliar(boolean isFamiliar) {
        this.isFamiliar = isFamiliar;
    }

    public String getShanbayId() {
        return shanbayId;
    }

    public void setShanbayId(String shanbayId) {
        this.shanbayId = shanbayId;
    }

    @Override
    public int hashCode() {
        String lowerCase = word.toLowerCase();
        return lowerCase.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Word other = (Word) obj;
        String lowerCase = word.toLowerCase();
        return lowerCase.equals(other.word.toLowerCase());
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
}
