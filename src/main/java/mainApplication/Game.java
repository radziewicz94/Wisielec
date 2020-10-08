package mainApplication;

import dbo.DbConnection;
import dbo.WordsDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private Scanner scanner = new Scanner(System.in);
    private DbConnection dbConnection = new DbConnection();
    WordsDatabase wordsDatabase;

    public void getRandomWorld(List<String> getWord) {
        Random random = new Random();
        List<WordsDatabase> wordList = new ArrayList<>();
        System.out.println("Wylosowane słowo to");
        int id = random.nextInt(getWord.size());
        String word = getWord.get(id);
        hideWord(word);
    }

    private void hideWord(String word) {

        StringBuilder words = new StringBuilder(word);
        StringBuilder newWord = null;
        String result = word;
        char[] chars = result.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isWhitespace(word.charAt(i))) {
                newWord = words.replace(i, i + 1, "*");

            }
        }
        System.out.println(word);
        guessWord(word, newWord);
    }

    private void guessWord(String word, StringBuilder hiddenWord) {
        word = word.toLowerCase();

        int points = 8;
        boolean flag = false;
        do{
            String letter = getLetter();
            int value = word.indexOf(letter);
            if(points > 0) {
                try {
                    hiddenWord.replace(value, value + 1, letter);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("nie zgadłeś");
                    points--;
                }
            }
            if(word.equals(hiddenWord))
            {
                System.out.println("Wygrałeś, Podane słowo to " + hiddenWord);
                flag = true;
            }
            if(points == 0){
                flag = true;
            }
            System.out.println(hiddenWord);
        }while(!flag);
    }
    private String getLetter(){
        System.out.println("Podaj litere");
        String letter = scanner.nextLine();
        return letter;
    }
}
