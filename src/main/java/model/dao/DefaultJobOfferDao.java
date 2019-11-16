package model.dao;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.entities.Company;
import model.entities.JobOffer;

/**
 * Classe del Dao dell'offerta di lavoro.
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Transactional
@Repository("jobOfferDao")
public class DefaultJobOfferDao extends DefaultDao implements JobOfferDao {

	@Override
	@Transactional
	public JobOffer create(String region, String province, String town, String position, String description,
			String contractType, String minEducationLevel, String minExperience, Company company) {
		JobOffer jobOffer = new JobOffer(region, province, town, position, description, contractType, minEducationLevel,
				minExperience, company);
		this.getSession().save(jobOffer);
		return jobOffer;
	}

	@Override
	@Transactional
	public JobOffer update(JobOffer jobOffer) {
		JobOffer merged = (JobOffer) this.getSession().merge(jobOffer);
		return merged;
	}

	@Override
	@Transactional
	public void delete(JobOffer jobOffer) {
		this.delete(jobOffer);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<JobOffer> findbyRegion(String region) {
		return null;
	}

}
