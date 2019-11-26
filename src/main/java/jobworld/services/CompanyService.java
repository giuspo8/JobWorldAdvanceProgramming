
/**
 * Interfaccia CompanyService
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */

package jobworld.services;


import java.util.List;

import jobworld.model.entities.Company;

public interface CompanyService {
List<Company> findAll();
	
Company findbyId(Long id);
	
Company create(String name, String email, String password, String description, String image, boolean roleAdmin);
		
Company update(Company company);
	
void delete(Company contact);

}
