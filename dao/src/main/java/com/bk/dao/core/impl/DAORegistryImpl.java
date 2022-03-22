package com.bk.dao.core.impl;

import com.bk.dao.core.api.DAORegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 09/03/22
 */
public class DAORegistryImpl implements DAORegistry {
    private final Map<Class, GenericDao> daoRegistry = new HashMap<>();;

    /**
     * Initializes GenericDao for all the given entities.<br>
     * Note that in this case, a No-Op implementation of DaoPrePostOperations is used.
     * @param entityClasses Set of entity classes for which Dao to be registered
     */
    public DAORegistryImpl(Set<Class> entityClasses) {
        init(entityClasses);
    }

    /**
     * Default constructor. <br>
     * Applications can use this to create an instance of DAORegistry and then register DAO for each entity separately.
     */
    public DAORegistryImpl() {

    }

    private void init(Set<Class> entityClasses) {
        NoOpDaoPrePostOperations noOpDaoPrePostOperations = new NoOpDaoPrePostOperations();
        for(Class clazz : entityClasses) {
            this.registerDAO(clazz, new GenericDao(clazz, noOpDaoPrePostOperations));
        }
    }

    @Override
    public void registerDAO(Class entityClass, GenericDao genericDao) {
        daoRegistry.put(entityClass, genericDao);
    }

    @Override
    public GenericDao getDao(Class entityClass) {
        return daoRegistry.get(entityClass);
    }
}