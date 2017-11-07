package me.iot.util.jpa.factory;

import me.iot.util.jpa.impl.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by tyf on 2016/5/22.
 */
public class BaseRepositoryFactory extends JpaRepositoryFactory {

    public BaseRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        return new BaseRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);
    }


    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return BaseRepositoryImpl.class;
    }

}
