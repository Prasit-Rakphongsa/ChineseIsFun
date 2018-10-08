package com.example.fong1.seniorproject;

/**
 * Created by fong1 on 5/13/2018.
 */

public class Word {
    private int id;
    private String word;
    private String pinyin;
    private String meaning;

    //Constructor

    public Word(int id,String word,String pinyin,String meaning) {
        this.id=id;
        this.word = word;
        this.pinyin = pinyin;
        this.meaning=meaning;
    }

    //Setter, getter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}

