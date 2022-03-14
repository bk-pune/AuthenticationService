package com.bk.authservice.service;

import java.io.Serializable;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 09/03/22
 */
public interface PrePostDataService<T> {
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
