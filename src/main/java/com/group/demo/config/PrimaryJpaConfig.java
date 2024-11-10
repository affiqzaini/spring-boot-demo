package com.group.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.group.demo.repository.primary", entityManagerFactoryRef = "primaryEntityManagerFactory", transactionManagerRef = "primaryTransactionManager")
public class PrimaryJpaConfig {
    private static final String[] ENTITYMANAGER_PACKAGES_TO_SCAN = {
            "com.group.demo.entity.primary.**",
    };

    @Value("${datasource.primary.ddl-auto}")
    private String ddlAuto;

    @Value("${datasource.primary.show-sql}")
    private String showSql;

    @Value("${datasource.primary.format-sql}")
    private String formatSql;

    @Value("${datasource.primary.dialect}")
    private String jpaDialect;

    @Autowired
    @Qualifier("primary")
    DataSource primaryDs;

    private HibernateJpaVendorAdapter vendorAdaptor() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        return vendorAdapter;
    }

    private Properties addProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
        properties.setProperty("hibernate.dialect", jpaDialect);
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.format_sql", formatSql);

        return properties;
    }

    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setJpaVendorAdapter(vendorAdaptor());
        emfb.setDataSource(primaryDs);
        emfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        emfb.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        emfb.setJpaProperties(addProperties());

        return emfb;
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    JpaTransactionManager primaryTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(primaryEntityManagerFactory().getObject());

        return transactionManager;
    }

}
