package me.iot.util.jpa;

import me.iot.util.jpa.factory.BaseRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by sylar on 16/5/26.
 */
@ConditionalOnProperty(name = "spring.datasource")
@Configuration
@EnableJpaRepositories(basePackages = "me.iot", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class JpaConfig {
}
