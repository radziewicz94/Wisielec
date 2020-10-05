package mainApplication;

import dbo.DbConnection;
import dbo.WordsDatabase;

import java.util.List;
import java.util.Random;

public class Game {
    private DbConnection dbConnection = new DbConnection();

    public void getRandomWorld(List<WordsDatabase> getWord){
        Random random = new Random();

        System.out.println("Wylosowane słowo to");
        int id = random.nextInt(getWord.size());
        System.out.println(getWord.get(id));
        System.out.println(hideWord(getWord.get(id)));
    }

    /*private String hideWord(WordsDatabase word){
        char[] hidden = word.toString().toCharArray();
        char newWord =
        System.out.println(newWord);
        *//*for (int i = 0; i < hidden.length; i++) {
            hidden = word.replaceAll(hidden, "*")
        }*//*
        return newWord;
    }*/
}
