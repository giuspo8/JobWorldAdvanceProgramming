/**
 * Implementazione dell'interfaccia CompanyService
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
import jobworld.model.dao.CompanyDao;
import jobworld.model.dao.JobOfferDao;
import jobworld.model.entities.Company;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.User;

@Transactional
@Service("companyService")
public class CompanyServiceDefault implements CompanyService{

private CompanyDao companyRepository;
private JobOfferDao jobOfferRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Company findbyId(Long id) {
		return this.companyRepository.findbyId(id);
	}
	
	
	@Transactional(readOnly=true)
	@Override
	public Company findbyUserId(String id) {
		return this.companyRepository.findbyUserId(id);
	}

	@Transactional(readOnly=true)
	public List<Company> findAll() {
		return this.companyRepository.findAll();
	}


	@Transactional
	@Override
	public Company create(String name,User user) {
		Company company = new Company(name,user);
		user.setCompany(company);
		return this.companyRepository.create(company);
	}
	
	@Transactional
	@Override
	public Company update(Company company) {
		return this.companyRepository.update(company);
	}

	@Transactional
	@Override
	public void delete(Company company) {
		for (JobOffer j:company.getJobOffers()) {
			jobOfferRepository.delete(j);
		}
		company.getJobOffers().clear();
		company=companyRepository.update(company);
		companyRepository.delete(company); 
	}


	@Autowired
	public void setCompanyRepository(CompanyDao companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	@Autowired
	public void setjobOfferRepository(JobOfferDao jobOfferRepository) {
		this.jobOfferRepository = jobOfferRepository;
	}
}
