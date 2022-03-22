package com.bk.dao.core.impl;

import com.bk.dao.core.api.DaoPrePostOperations;

import java.io.Serializable;

/**
 * Default DaoPrePostOperations implementation.<p>
 * Application can provide entity wise implementations and provide them at the time of DAO initialization.<p>
 *
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 15/03/22
 */
public class NoOpDaoPrePostOperations implements DaoPrePostOperations {
    @Override
    public Object prePersistSingle(Object o) {
        // That's how you invoke default method from the interface !
        return DaoPrePostOperations.super.prePersistSingle(o);
    }

    @Override
    public Object postPersistSingle(Object o) {
        return DaoPrePostOperations.super.postPersistSingle(o);
    }

    @Override
    public Object preFetchSingle(Serializable id) {
        return DaoPrePostOperations.super.preFetchSingle(id);
    }

    @Override
    public Object postFetchSingle(Object o) {
        return DaoPrePostOperations.super.postFetchSingle(o);
    }
}
