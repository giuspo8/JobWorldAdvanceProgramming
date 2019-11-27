package jobworld.model.dao;


import java.util.List;


import jobworld.model.entities.Company;

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
	
	Company create(String name);

	Company update(Company company);

	void delete(Company company);

	Company findbyId(long id);
	
	List<Company> findAll();
	

	
	
	
}
