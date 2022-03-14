package com.bk.authservice.service;

import com.bk.authservice.entity.YaasEntity;
import com.bk.dao.core.DAORegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Generic data service, which provides business level APIs and also hides the underlying DAO implementation.
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 04/03/22
 */
@Component
public class GenericDataProviderService implements DataService<YaasEntity> {
    @Autowired
    private DAORegistry daoRegistry;


    @Override
    public YaasEntity persistSingle(YaasEntity yaasEntity) {
        return null;
    }

    @Override
    public YaasEntity fetchSingle(Class<? extends YaasEntity> entityClass, Long id) {
        YaasEntity yaasEntity = daoRegistry.getDao(entityClass).fetchSingle(id);
        yaasEntity = (YaasEntity) postPersistSingle(yaasEntity);
        return yaasEntity;
    }

    @Override
    public List<YaasEntity> fetchWithCriteria(Class<? extends YaasEntity> entityClass, Map<String, Object> fetchConditions) {
        return daoRegistry.getDao(entityClass).fetchWithCriteria(fetchConditions);
        //TODO postFetchWithCriteria
    }


}