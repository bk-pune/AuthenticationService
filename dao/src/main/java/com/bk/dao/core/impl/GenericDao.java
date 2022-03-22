package com.bk.dao.core.impl;

import com.bk.dao.core.api.DaoPrePostOperations;
import com.bk.dao.session.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

/**
 * Generic Data Access Layer for performing CRUD operations over entities.
 * <p>
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 03/03/22
 */
public class GenericDao implements DaoPrePostOperations {

    private DaoPrePostOperations daoPrePostOperations;

    private Class entityClass;
    private SessionFactory sessionFactory;

    public GenericDao(Class entityClass, DaoPrePostOperations daoPrePostOperations) {
        this.entityClass = entityClass;
        sessionFactory = new HibernateUtil(entityClass).getSessionFactory();
    }

    public <T> T persistSingle(T entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        entity = (T) prePersistSingle(entity);
        currentSession.persist(entity);
        transaction.commit();
        entity = (T) postPersistSingle(entity);
        return entity;
    }

    public <T> T fetchSingle(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        T entity = (T) currentSession.get(entityClass, id);
        entity =  (T) postFetchSingle(entity);
        transaction.commit();
        return entity;
    }

    public <T> List<T> fetchWithCriteria(Map<String, Object> fetchConditions) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery(entityClass);
        Root root = query.from(entityClass);

        Predicate predicate = null;
        for(Map.Entry<String, Object> condition: fetchConditions.entrySet()) {
            Predicate nextPredicate = criteriaBuilder.equal(root.get(condition.getKey()), condition.getValue());
            if(predicate != null) {
                predicate = criteriaBuilder.and(predicate, nextPredicate);
            } else { // for first predicate or only one predicate
                predicate = nextPredicate;
            }
        }
        query.where(predicate);
        List<T> resultList = currentSession.createQuery(query).getResultList();
        for(T t:resultList) {
            t = (T) postFetchSingle(t); // pass by reference
        }
        transaction.commit();
        return resultList;
    }
}
