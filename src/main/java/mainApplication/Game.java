package mainApplication;

import dbo.DbConnection;
import dbo.WordsDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private DbConnection dbConnection = new DbConnection();
    WordsDatabase wordsDatabase;

    public void getRandomWorld(List<String> getWord) {
        Random random = new Random();
        List<WordsDatabase> wordList = new ArrayList<>();
        System.out.println("Wylosowane słowo to");
        int id = random.nextInt(getWord.size());
        String word = getWord.get(id);
        System.out.println(hideWord(word));
    }

    private StringBuilder hideWord(String word) {

        StringBuilder words = new StringBuilder(word);
        StringBuilder newWord = null;
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isWhitespace(word.charAt(i))) {
                newWord = words.replace(i, i + 1, "*");
            }
        }
        System.out.println(word);
        guessWord("test", newWord);
        return newWord;
    }

    private void guessWord(String word, StringBuilder hiddenWord) {

    }
}
