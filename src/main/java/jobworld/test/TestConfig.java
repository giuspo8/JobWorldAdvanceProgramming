package jobworld.test;
/**
 * Classe DataServiceConfig per far partire la Webapp
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
import java.util.Properties;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jobworld.app.DataServiceConfig;

@Configuration
@ComponentScan(basePackages = {"jobworld.model","jobworld.services"})
@EnableTransactionManagement
public class TestConfig extends DataServiceConfig {

	@Override
	protected Properties hibernateProperties() {
		Properties hibernateProp = super.hibernateProperties();
		hibernateProp.put("javax.persistence.schema-generation.database.action", "drop-and-create");
		return hibernateProp;
	}
	

}
