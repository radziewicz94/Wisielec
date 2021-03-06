package model;

import javax.persistence.*;

@Entity
public class WordsDatabase {
    public static int wordCount = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "word")
    private String word;

    public WordsDatabase() {
    }

    public WordsDatabase(String word) {
        this.word = word;
        wordCount++;
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

    @Override
    public String toString() {
        return "Id " + id + " Słowo: " + word;
    }
}
