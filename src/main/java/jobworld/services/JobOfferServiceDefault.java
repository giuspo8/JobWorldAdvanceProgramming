/**
 * Implementazione dell'interfaccia JobOfferService
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
package jobworld.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import jobworld.model.dao.JobOfferDao;
import jobworld.model.entities.Company;
import jobworld.model.entities.JobOffer;


@Transactional
@Service("jobofferService")
public class JobOfferServiceDefault implements JobOfferService{
private JobOfferDao jobofferRepository;
	


	@Transactional(readOnly=true)
	public List<JobOffer> findAll() {
		return this.jobofferRepository.findAll();
	}

	@Transactional
	@Override
	public JobOffer create(String region, String province, String town, String position, String description,
			String contractType, String minEducationLevel, String minExperience, Company company) {
		return this.jobofferRepository.create(region,province,town,position,description,contractType,minEducationLevel,minExperience,company);

	}
	
	@Transactional
	@Override
	public JobOffer update(JobOffer joboffer) {
		return this.jobofferRepository.update(joboffer);
	}

	@Transactional
	@Override
	public void delete(JobOffer joboffer) {
		this.jobofferRepository.delete(joboffer);
	}


	@Autowired
	public void setJobOfferRepository(JobOfferDao jobofferRepository) {
		this.jobofferRepository = jobofferRepository;
	}
}
