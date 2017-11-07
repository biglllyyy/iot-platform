package me.iot.util.mongo;

import me.iot.util.mongo.impl.BaseMongoRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by sylar on 16/5/26.
 */
//@ConditionalOnProperty(name = "spring.data.mongodb")
@Configuration
@EnableMongoRepositories(basePackages = "me.iot", repositoryBaseClass = BaseMongoRepositoryImpl.class)
public class MongoConfig {
}
