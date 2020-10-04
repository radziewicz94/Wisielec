import dbo.WordsDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {
    WordsDatabase wordsDatabase = new WordsDatabase();

    @Test
    public void addWord(){
        Assertions.assertNotNull(wordsDatabase.getId());
        wordsDatabase.setWord("test");
        String word = wordsDatabase.getWord();
        assertNotNull(word);
    }



}