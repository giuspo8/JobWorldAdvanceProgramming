package jobworld.app;
/**
 * Classe DataServiceConfig per far partire la Webapp
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"jobworld.model"})
@EnableTransactionManagement
@PropertySource("classpath:dbconfig.properties")
public class DataServiceConfig {

	@Value("${db.host}")
	private String db_host;
	@Value("${db.port}")
	private String db_port;
	@Value("${db.name}")
	private String db_name;
	@Value("${db.username}")
	private String db_usn;
	@Value("${db.password}")
	private String db_pw;
	private static Logger logger = LoggerFactory.getLogger(DataServiceConfig.class);

	// metodo utilizzato per inizializzare le proprietà usando l'annotazione
	// @Value, contenute nel file src/main/resources/dbconfig.properties
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public DataSource dataSource() {
		try {
			
			DriverManagerDataSource ds = new DriverManagerDataSource();
			ds.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
			ds.setUrl("jdbc:mysql://" + db_host + ":" + db_port + "/" + db_name + "?createDatabaseIfNotExist=true");
			ds.setUsername(db_usn);
			ds.setPassword(db_pw);
			return ds;

		} catch (Exception e) {
			logger.error("DataSource bean cannot be created!", e);
			return null;
		}
	}

	private Properties hibernateProperties() {
		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProp.put("hibernate.format_sql", true);
		hibernateProp.put("hibernate.use_sql_comments", true);
		hibernateProp.put("hibernate.show_sql", false);
		hibernateProp.put("hibernate.max_fetch_depth", 3);
		hibernateProp.put("hibernate.jdbc.batch_size", 10);
		hibernateProp.put("hibernate.jdbc.fetch_size", 50);
//		    hibernateProp.put("hibernate.enable_lazy_load_no_trans", true);
		hibernateProp.put("javax.persistence.schema-generation.database.action", "drop-and-create");
		return hibernateProp;
	}

	@Bean
	public SessionFactory sessionFactory() throws IOException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan("jobworld");
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws IOException {
		return new HibernateTransactionManager(sessionFactory());
	}

}
