package com.poc.multidb.second.dbconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "managerEntityManagerFactory",
        transactionManagerRef = "managerTransactionManager",
        basePackages = { "com.poc.multidb.second" }
)
@EntityScan("com.poc.multidb.second.entity")
public class ManagerDBConfig {

    @Bean(name="managerDataSource")
    @ConfigurationProperties(prefix="spring.seconddatasource")
    public DataSource managerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "managerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean managerEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                              @Qualifier("managerDataSource") DataSource managerDataSource) {
        return builder
                .dataSource(managerDataSource)
                .packages("com.poc.multidb.second")
                .build();
    }

    @Bean(name = "managerTransactionManager")
    public PlatformTransactionManager managerTransactionManager(
            @Qualifier("managerEntityManagerFactory") EntityManagerFactory managerEntityManagerFactory) {
        return new JpaTransactionManager(managerEntityManagerFactory);
    }
}
