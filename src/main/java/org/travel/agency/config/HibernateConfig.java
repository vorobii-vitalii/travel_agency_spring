package org.travel.agency.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.travel.agency.exceptions.JdbcDriverClassNotFoundException;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Class that configures Hibernate for future usage
 * Note that configuration properties must be included in
 * db.properties file
 * @see Properties
 */
@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:db.properties"})
@AllArgsConstructor
public class HibernateConfig {
    private final Environment environment;

    /**
     * The method extracts properties from a file
     * and puts it in Properties object
     * @see Properties
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    /**
     * The method assembles DataSource object with properties,
     * extracted from `properties` file
     * If name of JDBC driver class cannot be loaded,
     * than below mentioned runtime exception is occurred
     * @see DataSource
     * @see JdbcDriverClassNotFoundException
     */
    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource myDataSource = new ComboPooledDataSource();
        try {
            myDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new JdbcDriverClassNotFoundException();
        }
        myDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        myDataSource.setUser(environment.getProperty("jdbc.username"));
        myDataSource.setPassword(environment.getProperty("jdbc.password"));
        myDataSource.setInitialPoolSize(retrieveIntProperty("connection.pool.initialPoolSize"));
        myDataSource.setMinPoolSize(retrieveIntProperty("connection.pool.minPoolSize"));
        myDataSource.setMaxPoolSize(retrieveIntProperty("connection.pool.maxPoolSize"));
        myDataSource.setMaxIdleTime(retrieveIntProperty("connection.pool.maxIdleTime"));
        return myDataSource;
    }

    /**
     * The method stands for creating sessions factory
     * as well as setting up packages to scan
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("org.travel.agency.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /**
     * Set-up transaction manager to use @Transactional annotation in further
     * @see HibernateTransactionManager
     * @see org.springframework.transaction.annotation.Transactional
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    private int retrieveIntProperty(String property) {
        return Integer.parseInt(environment.getRequiredProperty(property));
    }

}