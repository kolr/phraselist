package com.phraselist.components.data.hbnt.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 25.07.2016
 * Created by Rodion.
 */
public class HibernateUtil {
    private static final Logger LOG = Logger.getLogger(HibernateUtil.class);

    private static final String CONFIGURATION_LOADED = "Hibernate Configuration has been loaded.";
    private static final String REGISTRY_LOADED = "Hibernate Annotation Service Registry has been created.";
    private static final String CREATION_FAILED = "Initial Session Factory creation failed.";

    private static SessionFactory sessionAnnotationFactory;

    public static SessionFactory getSessionAnnotationFactory() {
        if (sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }

    private static SessionFactory buildSessionAnnotationFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            LOG.info(CONFIGURATION_LOADED);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties()).build();
            LOG.info(REGISTRY_LOADED);
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (Throwable ex) {
            LOG.error(CREATION_FAILED);
            LOG.error(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
