package com.phraselist.components.services.user.hbnt.impl;

import com.phraselist.components.data.hbnt.entities.*;
import com.phraselist.components.data.hbnt.util.HibernateUtil;
import com.phraselist.components.services.user.PhraseService;
import com.phraselist.components.services.user.UserService;
import com.phraselist.exceptions.login.UserException;
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
public class PhraseServiceImpl implements PhraseService {
    private static final Logger LOG = Logger.getLogger(PhraseServiceImpl.class);

    @Inject
    private UserService userService;

    public OriginalWord getOriginalWord(String word) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from OriginalWord where word = :word");
        query.setParameter("word", word);
        OriginalWord result = (OriginalWord) query.uniqueResult();
        session.close();
        return result;
    }

    public Translation getTranslation(String word) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Translation where tword = :tword");
        query.setParameter("tword", word);
        Translation result = (Translation) query.uniqueResult();
        session.close();
        return result;
    }

    public OriginalLanguage getOriginalLanguage(String language) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from OriginalLanguage where language = :language");
        query.setParameter("language", language);
        OriginalLanguage result = (OriginalLanguage) query.uniqueResult();
        session.close();
        return result;
    }

    public TranslatedLanguage getTranslatedLanguage(String language) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from TranslatedLanguage where tlanguage = :tlanguage");
        query.setParameter("tlanguage", language);
        TranslatedLanguage result = (TranslatedLanguage) query.uniqueResult();
        session.close();
        return result;
    }

    public List<Item> getUsersItems(String oLanguage, String tLanguage, String login) {
        OriginalLanguage originalLanguage = getOriginalLanguage(oLanguage);
        TranslatedLanguage translatedLanguage = getTranslatedLanguage(tLanguage);
        User user;
        try {
            user = userService.getUserByLogin(login);
        } catch (UserException e) {
            LOG.error(e);
            return null;
        }
        if (user != null && originalLanguage != null && translatedLanguage != null) {
            return getItems(originalLanguage, translatedLanguage, user);
        }
        return null;
    }

    private List<Item> getItems(OriginalLanguage oLang, TranslatedLanguage tLang, User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
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
}