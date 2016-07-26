package com.phraselist.components.services.user.hbnt.impl;

import com.phraselist.components.data.hbnt.entities.OriginalLanguage;
import com.phraselist.components.data.hbnt.entities.OriginalWord;
import com.phraselist.components.data.hbnt.entities.TranslatedLanguage;
import com.phraselist.components.data.hbnt.entities.Translation;
import com.phraselist.components.data.hbnt.util.HibernateUtil;
import com.phraselist.components.services.user.PhraseService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * 26.07.2016
 * Created by Rodion.
 */
public class PhraseServiceImpl implements PhraseService {
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
}
