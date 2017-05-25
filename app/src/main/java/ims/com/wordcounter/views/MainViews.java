package ims.com.wordcounter.views;

import java.util.List;

import ims.com.wordcounter.models.Word;

public interface MainViews {

    void showMessage(String msg);
    void displayWords(List<Word> wordList);

}