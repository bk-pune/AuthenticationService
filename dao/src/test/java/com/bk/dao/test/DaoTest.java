package com.bk.dao.test;

import com.bk.dao.model.TestUserEntity;
import com.bk.dao.core.GenericDao;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 07/03/22
 */
public class DaoTest {

    @Test
    public void testPersistSingle() {
        GenericDao dao = new GenericDao(TestUserEntity.class);
        TestUserEntity t = (TestUserEntity) dao.persistSingle(getTestUserEntity());
        Assert.assertNotNull(t.getId());
    }

    private TestUserEntity getTestUserEntity() {
        TestUserEntity t = new TestUserEntity();
        t.setUsername("shruti");
        t.setEmail("sg@gmail.com");
        return t;
    }
}