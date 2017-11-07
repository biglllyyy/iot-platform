package me.iot.util.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by tyf on 2016/5/22.
 */
@NoRepositoryBean
public interface BaseMongoRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {
    Page<T> find(Criteria criteria);

    Page<T> find(Criteria criteria, Pageable pageable);

    Page<T> find(Query query);

    Page<T> find(Query query, Pageable pageable);

    T findOne(Criteria criteria);

    T findOne(Query query);
}
