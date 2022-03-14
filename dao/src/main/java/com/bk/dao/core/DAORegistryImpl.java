package com.bk.dao.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 09/03/22
 */
public class DAORegistryImpl implements DAORegistry {
    private final Map<Class, GenericDao> daoRegistry;

    public DAORegistryImpl(Set<Class> entityClasses) {
        daoRegistry = new HashMap<>();
        init(entityClasses);
    }

    private void init(Set<Class> entityClasses) {
        for(Class clazz : entityClasses) {
            daoRegistry.put(clazz, new GenericDao(clazz));
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