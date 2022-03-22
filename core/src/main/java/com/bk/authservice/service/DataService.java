package com.bk.authservice.service;

import java.util.List;
import java.util.Map;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 04/03/22
 */
public interface DataService<T> {
    /**
     * Persists the given entity in persistent storage.
     *
     * @param t Entity instance
     * @return The same entity instance, with auto generated db field values
     */
    T persistSingle(T t);

    /**
     * Select the entity from persistent storage.
     * @param id
     * @return First matching entity
     */
    T fetchSingle(Class<? extends T> entityClass, Long id);

    /**
     * Fetch all the entities matching given conditions
     * @param entityClass the Entity Class
     * @param fetchConditions Where clauses to be added as fetch criteria - key is name of field in entity and the value is actual value to match in db
     * @return List of this entity matching the given conditions
     */
    List<T> fetchWithCriteria(Class<? extends T> entityClass, Map<String, Object> fetchConditions);
}
