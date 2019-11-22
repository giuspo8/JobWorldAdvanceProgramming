package services;

import java.time.LocalDate;
import java.util.List;

import model.entities.Company;

public interface JobOfferService {
	List<Company> findAll();
	
	Company findById(Long id);
		
	Company create(String string, String string2);
		
	Company create(String string, String string2, LocalDate birthdate);
		
	Company update(Company company);
		
		void delete(Company contact);
}
