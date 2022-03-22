package com.bk.dao.core.api;

import com.bk.dao.core.impl.GenericDao;

/**
 * Registry for DAOs used
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 09/03/22
 */
public interface DAORegistry {
    /**
     * Register the given DAO instance to the registry.
     *
     * @param genericDao DAO for a specific entity such User
     */
    void registerDAO(Class entityClass, GenericDao genericDao);

    /**
     * Get DAO for the given entity class.
     * @param entityClass
     * @return
     */
    GenericDao getDao(Class entityClass);
}
