/**
 * Interfaccia JobOfferService
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
package services;

import java.util.List;

import model.entities.Company;
import model.entities.JobOffer;

public interface JobOfferService {
	List<JobOffer> findAll();
	
	JobOffer findbyRegion(String region);
		
	JobOffer create(String region, String province, String town, String position, String description,
			String contractType, String minEducationLevel, String minExperience, Company company);
		
	JobOffer create(String string, String string2);
		
	JobOffer update(JobOffer joboffer);
		
		void delete(JobOffer contact);
}
