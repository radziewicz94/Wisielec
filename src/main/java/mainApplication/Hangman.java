package mainApplication;

import dbo.DbConnection;
import dbo.WordsDatabase;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Hangman {

    DbConnection dbConnection = new DbConnection();
    Scanner scanner = new Scanner(System.in);
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
                    case PLAY:
                        break;

                }

        }while (option != Option.EXIT);
    }
    private void deleteOrUpdate(){
        boolean flag = false;
        do {
            System.out.println("Podaj id słowa, która ma zostać zaktualizowane lub usunięte");
            System.out.println("1 - zaktualizuje");
            System.out.println("2 - usuń");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        dbConnection.dbConfig(new WordsDatabase(word));

    }
    private void printWords(){
        dbConnection.getAllWords();
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
        PLAY(4, "Graj");

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
