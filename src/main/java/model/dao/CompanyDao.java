package model.dao;

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
	Company create(String email, String password, String description);

	Company update(Company company);

	void delete(Company company);

	Company findbyId(long id);
}