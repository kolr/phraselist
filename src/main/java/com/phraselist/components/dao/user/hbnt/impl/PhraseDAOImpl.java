package com.phraselist.components.dao.user.hbnt.impl;

import com.phraselist.components.dao.user.PhraseDAO;
import com.phraselist.components.data.hbnt.entities.*;
import com.phraselist.components.data.hbnt.util.HibernateUtil;
import com.phraselist.components.dao.user.UserDAO;
import com.phraselist.exceptions.login.UserException;
import com.phraselist.model.beans.db.ItemBean;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.inject.Inject;
import java.util.List;

/**
 * 26.07.2016
 * Created by Rodion.
 */
public class PhraseDAOImpl implements PhraseDAO {
    private static final Logger LOG = Logger.getLogger(PhraseDAOImpl.class);

    private SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();

    @Inject
    private UserDAO userDAO;

    public OriginalWord getOriginalWord(String word) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from OriginalWord where word = :word");
        query.setParameter("word", word);
        OriginalWord result = (OriginalWord) query.uniqueResult();
        session.close();
        return result;
    }

    public OriginalWord addOriginalWord(String word) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(new OriginalWord(word));
        transaction.commit();
        return getOriginalWord(word);
    }


    public Translation getTranslation(String word) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Translation where tword = :tword");
        query.setParameter("tword", word);
        Translation result = (Translation) query.uniqueResult();
        session.close();
        return result;
    }

    public Translation addTranslation(String word) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(new Translation(word));
        transaction.commit();
        return getTranslation(word);
    }

    public OriginalLanguage getOriginalLanguage(String language) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from OriginalLanguage where language = :language");
        query.setParameter("language", language);
        OriginalLanguage result = (OriginalLanguage) query.uniqueResult();
        session.close();
        return result;
    }

    public OriginalLanguage addOriginalLanguage(String language) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(new OriginalLanguage(language));
        transaction.commit();
        return getOriginalLanguage(language);
    }

    public TranslatedLanguage getTranslatedLanguage(String language) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from TranslatedLanguage where tlanguage = :tlanguage");
        query.setParameter("tlanguage", language);
        TranslatedLanguage result = (TranslatedLanguage) query.uniqueResult();
        session.close();
        return result;
    }

    public TranslatedLanguage addTranslatedLanguage(String language) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(new TranslatedLanguage(language));
        transaction.commit();
        return getTranslatedLanguage(language);
    }

    public List<Item> getUsersItems(String oLanguage, String tLanguage, String login) {
        OriginalLanguage originalLanguage = getOriginalLanguage(oLanguage);
        TranslatedLanguage translatedLanguage = getTranslatedLanguage(tLanguage);
        User user;
        try {
            user = userDAO.getUserByLogin(login);
        } catch (UserException e) {
            LOG.error(e);
            return null;
        }
        if (user != null && originalLanguage != null && translatedLanguage != null) {
            return getItems(originalLanguage, translatedLanguage, user);
        }
        return null;
    }

    public Item getItem(User user, OriginalWord oWord, Translation tWord) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Item where user = :user " +
                "and originalWord = :originalWord and translation = :translation");
        query.setParameter("user", user);
        query.setParameter("originalWord", oWord);
        query.setParameter("translation", tWord);
        Item result = (Item) query.uniqueResult();
        session.close();
        return result;
    }

    private List<Item> getItems(OriginalLanguage oLang, TranslatedLanguage tLang, User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Item where originalLanguage = :oLang " +
                "and translatedLanguage = :tLang and user = :user");
        query.setParameter("oLang", oLang);
        query.setParameter("tLang", tLang);
        query.setParameter("user", user);
        List<Item> result = query.list();
        session.close();
        return result;
    }

    // TODO think about.
    public Item addItem(ItemBean item, String originalLanguage, String translatedLanguage) throws UserException {
        Item itemToAdd = new Item();
        User user = userDAO.getUserByLogin(item.getLogin());
        OriginalWord oWord = getOriginalWord(item.getForeign());
        if (oWord == null) {
            oWord = addOriginalWord(item.getForeign());
        }
        Translation tWord = getTranslation(item.getTranslation());
        if (tWord == null) tWord = addTranslation(item.getTranslation());
        OriginalLanguage oLang = getOriginalLanguage(originalLanguage);
        if (oLang == null) oLang = addOriginalLanguage(originalLanguage);
        TranslatedLanguage tLang = getTranslatedLanguage(translatedLanguage);
        if (tLang == null) tLang = addTranslatedLanguage(translatedLanguage);
        itemToAdd.setUser(user);
        itemToAdd.setDateOfCreation(item.getDateOfCreation());
        itemToAdd.setComment(item.getComment());
        itemToAdd.setDateOfEdition(item.getDateOfEdition());
        itemToAdd.setOriginalLanguage(oLang);
        itemToAdd.setTranslatedLanguage(tLang);
        itemToAdd.setOriginalWord(oWord);
        itemToAdd.setTranslation(tWord);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(itemToAdd);
        transaction.commit();
        return getItem(user, oWord, tWord);
    }

    public void deleteItem(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Item where id=:id");
        query.setLong("id", id);
        query.executeUpdate();
        transaction.commit();
        LOG.info(String.format("Item with id %d has been deleted.", id));
    }

}