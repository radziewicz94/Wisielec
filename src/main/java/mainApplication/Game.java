package mainApplication;

import dbo.DbConnection;
import model.Players;
import model.User;
import model.WordsDatabase;

import java.util.*;

import static mainApplication.Hangman.wordList;

public class Game {

    private int counts = 8;
    private int id = 0;
    private Scanner scanner = new Scanner(System.in);
    private DbConnection dbConnection = new DbConnection();
    private List<String> getWords = new ArrayList<>();

    public void getWords(List<String> wordList) {
        getWords = wordList;
        getRanomWord();
    }
    public void getRanomWord(){
        Random random = new Random();
        int id = random.nextInt(wordList.size());
        String word = getWords.get(id);
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
        User name = Players.player(id);
        System.out.println("Gracz " + name.getUserName());
        guessWord(word, newWord);
    }

    private void guessWord(String word, StringBuilder hiddenWord) {
        word = word.toLowerCase();
        boolean playsOn = true;
        String options = "";
        System.out.println("ukryte słowo " + hiddenWord);
        do {

            char letter = getLetter();
            int value = word.indexOf(letter);
            if (!word.contains(String.valueOf(letter))) {
                counts--;
            }
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    hiddenWord.replace(i, i + 1, String.valueOf(letter));
                }
            }
            if (word.contentEquals(hiddenWord)) {
                System.out.println("Wygrałeś, Podane słowo to " + hiddenWord + " użytkownika " +
                        Players.player(id).getUserName() + " zdobywa " + counts + " punktów");
                id++;
                playsOn = false;
                System.out.println("Grasz dalej?\ntak / nie");
                options = scanner.nextLine();
            } else if (counts == 0) {
                id++;
                playsOn = false;
                System.out.println("Przegrałeś, miałeś już 8 prób");
                System.out.println("Grasz dalej?\ntak / nie");
                options = scanner.nextLine();
            }
            System.out.println(hiddenWord);
            options.toLowerCase();
            if(!playsOn && !options.equals("nie")){
                getRanomWord();
            }

        } while (!options.equals("nie"));

    }

    private char getLetter() {
        System.out.println("Podaj litere");
        char letter = scanner.next().charAt(0);
        System.out.println(letter);
        return letter;
    }
}
