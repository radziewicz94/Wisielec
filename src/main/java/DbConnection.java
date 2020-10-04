import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbConnection {

    public void dbConfig(WordsDatabase wordsDatabase){
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(WordsDatabase.class);
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(wordsDatabase);
        session.getTransaction().commit();
        factory.close();

    }

    public void getAllWords(){
        //HashMap<Integer, String> wordsList = new HashMap<>();
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(WordsDatabase.class);
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<WordsDatabase> wordsList = session.createQuery("from WordsDatabase").getResultList();
        for(WordsDatabase wordsDatabase : wordsList){
            System.out.println(wordsDatabase.toString());
        }
    }
    public void updateWord(int id, String word){
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(WordsDatabase.class);
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        WordsDatabase wordsDatabase = session.get(WordsDatabase.class, id);
        wordsDatabase.setWord(word);
        session.update(wordsDatabase);
        session.getTransaction().commit();
        factory.close();
    }
}
