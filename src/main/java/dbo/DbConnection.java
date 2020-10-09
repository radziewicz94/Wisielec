package dbo;

import model.User;
import model.WordsDatabase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class DbConnection {

    public void addWord(WordsDatabase wordsDatabase){
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
    public void addUser(User user){

    }

    public List<WordsDatabase> getAllWords(){
        //HashMap<Integer, String> wordsList = new HashMap<>();
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(WordsDatabase.class);
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<WordsDatabase> wordsList = session.createQuery("from model.WordsDatabase").getResultList();
        factory.close();
        return wordsList;
    }
    public List<String> getWord(){
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(WordsDatabase.class);
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String selectWord = "select word from WordsDatabase";
        Query query = session.createQuery(selectWord);
        List<String> result = query.getResultList();
        factory.close();

        return result;
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
    public void deleteWord(int id){
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(WordsDatabase.class);
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        WordsDatabase wordsDatabase = session.get(WordsDatabase.class, id);
        session.delete(wordsDatabase);
        session.getTransaction().commit();
        factory.close();
    }
}
