import dbo.DbConnection;
import model.WordsDatabase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DbConnectionTest {

    DbConnection dbConnection = new DbConnection();

    @Test
    public void shouldShowAllWords(){
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(WordsDatabase.class);
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<WordsDatabase> wordsList = session.createQuery("from model.WordsDatabase").getResultList();
        for (int i = 0; i < wordsList.size(); i++) {
            assertEquals("Id " + wordsList.get(i).getId() + " Słowo: " + wordsList.get(i).getWord(), wordsList.get(i).toString());
        }
    }
    @Test
    public void shouldUpdateWord(){
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(WordsDatabase.class);
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        WordsDatabase wordsDatabase = session.get(WordsDatabase.class, 3);
        wordsDatabase.setWord("Testowe słowo");
        session.update(wordsDatabase);
        session.getTransaction().commit();
        factory.close();

        Assertions.assertEquals("Id 3 Słowo: Testowe słowo", wordsDatabase.toString());
    }
}