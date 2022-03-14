package com.bk.dao.session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 03/03/22
 */
public class HibernateUtil {
    private static Class clazz;
    private static SessionFactory sessionFactory;

    public HibernateUtil(Class clazz) {
        this.clazz = clazz;
    }

    private static SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .configure("hibernate_config.xml")
                        .build();

                MetadataSources metadataSources = new MetadataSources(serviceRegistry);
                metadataSources.addAnnotatedClass(clazz);
                Metadata metadata = metadataSources.buildMetadata();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }
            return sessionFactory;
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if(sessionFactory != null)
            sessionFactory.close();
    }
}
