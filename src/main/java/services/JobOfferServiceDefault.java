package services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.dao.CompanyDao;
import model.dao.JobOfferDao;
import model.entities.Company;
import model.entities.JobOffer;


@Transactional
@Service("jobofferService")
public class JobOfferServiceDefault implements JobOfferService{
private JobOfferDao jobofferRepository;
	
	@Transactional(readOnly=true)
	@Override
	public JobOffer findById(Long id) {
		return this.jobofferRepository.findById(id);
	}

	@Transactional(readOnly=true)
	public List<JobOffer> findAll() {
		return this.jobofferRepository.findAll();
	}

	@Transactional
	@Override
	public JobOffer create(String firstName, String lastName) {
		return this.create(firstName, lastName, null);
	}

	@Transactional
	@Override
	public JobOffer create(String firstName, String lastName, LocalDate birthDate) {
		return this.jobofferRepository.create(firstName, lastName, birthDate);

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
