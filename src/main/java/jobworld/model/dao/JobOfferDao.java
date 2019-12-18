package jobworld.model.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import jobworld.model.entities.Company;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.JobOffer.Education;

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
	
	Session getSession();
	
	public void setSession(Session session);

	JobOffer create(String region, String province, String town, String position, String description,
			String contractType, Education minEducationLevel, String minExperience,LocalDate expiringDateCompany, Company company);

	JobOffer update(JobOffer jobOffer);

	void delete(JobOffer jobOffer);

	List<JobOffer> findAll();
	
	JobOffer findbyId(long id);
	
	Long getInterested(JobOffer jobOffer);

	List<JobOffer> filter(String region, String province, String town, String position, String contractType,
			String minEducationLevel, String minExperience);

	List<JobOffer> findbyCompanyId(long id);

}
