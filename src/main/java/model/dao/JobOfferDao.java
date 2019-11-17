package model.dao;

import java.util.Set;

import model.entities.Company;
import model.entities.JobOffer;

/**
 * Interfaccia del Dao dell'offerta di lavoro.
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

	Set<JobOffer> findbyRegion(String region);

}