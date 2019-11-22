package model.dao;


import java.util.List;


import model.entities.Company;

/**
 * Interfaccia del Dao dell'azienda.
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
public interface CompanyDao {
	static Company create(String email, String password, String description) {
		// TODO Auto-generated method stub
		return null;
	}

	Company update(Company company);

	void delete(Company company);

	Company findbyId(long id);
	
	
	
	List<Company> findAll();

	//List<Company> findAllWithAlbum();
	

	
	
	
}
