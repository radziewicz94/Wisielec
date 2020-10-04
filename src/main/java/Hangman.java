import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
                    case UPDATE_WORD:
                        updateWord();
                        break;
                    case PLAY:
                        break;

                }

        }while (option != Option.EXIT);
    }

    private void updateWord() {
        boolean flag = false;
        do {
            System.out.println("Podaj id słowa, jeśli nie pamiętasz możesz wyświetlić");
            System.out.println("1 - wyświetl");
            System.out.println("2 - nie wyświetlaj");
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                if (option == 1) {
                    printWords();
                } else if (option == 2) {
                    System.out.println("Podaj id");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Podaj nowe słowo");
                    String word = scanner.nextLine();
                    dbConnection.updateWord(id, word);
                    flag = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Niepoprawna wartość");
                scanner.next();
            }
        }while (!flag);
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
        UPDATE_WORD(3,"Zaktualizuj słowo"),
        PLAY(5, "Graj");

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
