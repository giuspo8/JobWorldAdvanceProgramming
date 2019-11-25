package jobworld.model.dao;

import java.util.List;

import jobworld.model.entities.Company;
import jobworld.model.entities.JobOffer;

/**
 * interfaccia JobOfferDao
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
public interface JobOfferDao {

	JobOffer create(String region, String province, String town, String position, String description,
			String contractType, String minEducationLevel, String minExperience, Company company);

	JobOffer update(JobOffer jobOffer);

	void delete(JobOffer jobOffer);

	List<JobOffer> findAll();
	
	JobOffer findbyId(long id);
	
/*
 	List<JobOffer> findbyRegion(String region);
 		
	List<JobOffer> filterByPosition(String keywords);

	List<JobOffer> orderedByPublicationDate();

	List<JobOffer> filterBypositionAndprovince(String position, String province);
	
	*/

	List<JobOffer> filter(String region, String province, String town, String position, String contractType,
			String minEducationLevel, String minExperience);

}
