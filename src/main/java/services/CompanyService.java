package services;


import java.util.List;

import model.entities.Company;

public interface CompanyService {
List<Company> findAll();
	
Company findbyId(Long id);
	
Company create(String email, String password, String description);
	
Company create(String string, String string2);
	
Company update(Company company);
	
	void delete(Company contact);

}
