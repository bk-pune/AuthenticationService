package com.bk.dao.core.api;

import java.io.Serializable;

/**
 * APIs to be invoked before and/or after any DAO API is invoked.<p>
 * It gives the applications the power of doing much more than just CRUD operations on the entities.<p>
 * A good use case is to fill up common fields such as creation date, modification date, modified by, etc.
 * <p>
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 09/03/22
 */
public interface DaoPrePostOperations<T> {
    /**
     * Define the operations to be performed <i>before</i> this entity is persisted in storage.<br>
     * Such operation could include modifying last updated, created timestamps.
     *
     * @param t
     * @return
     */
    default T prePersistSingle(T t) {
        return t;
    }

    /**
     * Define the operations to be performed <i>after</i> the entity is persisted in storage.<br>
     * This allows YAAS consumers to perform any dependent operation on this entity.
     *
     * @param t
     * @return
     */
    default T postPersistSingle(T t) {
        return t;
    }

    default T preFetchSingle(Serializable id) {
        return null;
    }

    default T postFetchSingle(T t) {
        return t;
    }
}
