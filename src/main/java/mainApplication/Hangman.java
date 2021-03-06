package mainApplication;

import dbo.DbConnection;
import model.Players;
import model.User;
import model.WordsDatabase;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    private final DbConnection dbConnection = new DbConnection();
    private Scanner scanner = new Scanner(System.in);
    private final WordsDatabase wordsDatabase = new WordsDatabase();
    private Game game = new Game();
    private Players player = new Players();
    private int id = 0;
    public static List<WordsDatabase> wordList;
    Hangman(){
        wordList = dbConnection.getAllWords();
    }
    public void applicationControl(){
        Option option;
        do {

                option = getOption();

                switch (option) {
                    case EXIT:
                        exit();
                        break;
                    case ADD_WORLD:
                        addWord();
                        break;
                    case PRINT_WORD:
                        printWords();
                        break;
                    case UPDATE_OR_DELETE_WORD:
                        deleteOrUpdate();
                        break;
                    case REGISTER_USER:
                        addUser();
                        break;
                    case PRINT_PLAYER:
                        player.printUsers();
                        break;
                    case PLAY:
                        game.getWords(dbConnection.getWord());
                        break;

                }

        }while (option != Option.EXIT);
    }

    private void addUser() {
        System.out.println("Podaj imie gracza");
        String playerName = scanner.nextLine();
        player.addUser(new User(playerName));
    }


    private void deleteOrUpdate(){
        boolean flag = false;
        do {
            System.out.println("Podaj id słowa, która ma zostać zaktualizowane lub usunięte");
            System.out.println("1 - zaktualizuje");
            System.out.println("2 - usuń");

            printWords();
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                if (option == 1) {
                    System.out.println("Podaj id");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Podaj nowe słowo");
                    String word = scanner.nextLine();
                    dbConnection.updateWord(id, word);
                    flag = true;
                }
                else if (option == 2) {
                    System.out.println("Podaj id");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    deleteWord(id);
                    flag = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Niepoprawna wartość");
                scanner.next();
            }
        }while (!flag);
    }
    private void deleteWord(int id){
        dbConnection.deleteWord(id);
    }
    private void updateWord(int id, String word) {
        dbConnection.updateWord(id, word);
    }

    private void addWord(){
        System.out.println("Słowo");
        String word = scanner.nextLine();
        dbConnection.addWord(new WordsDatabase(word));

    }
    private void printWords(){
        for (int i = 0; i < wordList.size(); i++) {
            System.out.println(wordList.get(i));
        }
    }
    private Option getOption(){
        boolean flag = false;
        Option option = null;
        while(!flag){
            printOptions();
            int select = scanner.nextInt();
            scanner.nextLine();
            try {
                option = option.selectOption(select);
                flag = true;
            }catch (NullPointerException e){
                System.out.println("nie ma takiej opcji");
            }
        }
        return option;
    }
    private void printOptions(){
        System.out.println("Wybierz opcje");
        for(Option option : Option.values()){
            System.out.println(option.toString());
        }
    }
    private void exit(){
        System.out.println("wychodzę z programu");
    }
    enum Option{
        EXIT(0, "wyjście"),
        ADD_WORLD(1, "Dodaj słowo"),
        PRINT_WORD(2, "Wyświetl słowa w bazie danych"),
        UPDATE_OR_DELETE_WORD(3,"Zaktualizuj lub usuń słowo"),
        REGISTER_USER(4, "Dodaj gracza"),
        PRINT_PLAYER(5, "Wyświetl graczy"),
        PLAY(6, "Graj");

        private int numberOfOption;
        private String description;

        Option(int numberOfOption, String description) {
            this.numberOfOption = numberOfOption;
            this.description = description;
        }

        public static Option selectOption(int number){

            return Option.values()[number];
        }

        @Override
        public String toString() {
            return numberOfOption + " - " + description;
        }
    }
}
