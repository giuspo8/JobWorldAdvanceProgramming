/**
 * Interfaccia JobOfferService
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
package jobworld.services;

import java.time.LocalDate;
import java.util.List;

import jobworld.model.entities.Company;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.JobOffer.Education;


public interface JobOfferService {
	List<JobOffer> findAll();
			
	JobOffer create(String region, String province, String town, String position, String description,
			String contractType, Education minEducationLevel, String minExperience,LocalDate expiringDate, Company company);
			
	JobOffer update(JobOffer joboffer);
		
	void delete(JobOffer contact);
	
	List<JobOffer> filter(String region, String province, String town, String position, String contractType,
			String minEducationLevel, String minExperience);
		
}
