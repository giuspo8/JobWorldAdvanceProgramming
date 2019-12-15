package jobworld.model.dao;


import java.util.List;

import org.hibernate.Session;

import jobworld.model.entities.Company;
import jobworld.model.entities.User;

/**
 * Interfaccia di CompanyDao
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
public interface CompanyDao {
	
	Session getSession();
	
	public void setSession(Session session);
	
	Company create(String name,User user);

	Company update(Company company);

	void delete(Company company);

	Company findbyId(long id);
	
	List<Company> findAll();

	Company findbyUserId(String id);
	

	
	
	
}
