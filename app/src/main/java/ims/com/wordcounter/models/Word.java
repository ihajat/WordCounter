package ims.com.wordcounter.models;


public class Word {
    private String word, frequency, primeNo;

    public Word(String title, String frequency, String primeNo) {
        this.word = title;
        this.frequency = frequency;
        this.primeNo = primeNo;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String name) {
        this.word = name;
    }

    public String getPrimeNo() {
        return primeNo;
    }

    public String getFrequency() {
        return frequency;
    }

}
