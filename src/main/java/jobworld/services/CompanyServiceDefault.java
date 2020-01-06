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
	
/**
 * Metodo che sovrascrive il findbyId
 * @param id l'id
 * 
 * @return la compagnia in base all'id
 */
	@Transactional(readOnly=true)
	@Override
	public Company findbyId(Long id) {
		return this.companyRepository.findbyId(id);
	}
	
	/**
	 * Metodo che sovrascrive il findbyUserid
	 * @param id l'id
	 * 
	 * @return la compagnia in base all'id dell'utente
	 */
	@Transactional(readOnly=true)
	@Override
	public Company findbyUserId(String id) {
		return this.companyRepository.findbyUserId(id);
	}
	/**
	 * Metodo che sovrascrive il findAll
	 * 
	 * 
	 * @return restituisce tutte le company
	 */
	@Transactional(readOnly=true)
	public List<Company> findAll() {
		return this.companyRepository.findAll();
	}

	/**
	 * Metodo che sovrascrive il create
	 * @param name è il nome
	 * @param user è l'utente
	 * 
	 * @return ritorna la company aggiornata dopo la create
	 */
	@Transactional
	@Override
	public Company create(String name,User user) {
		Company company = new Company(name,user);
		user.setCompany(company);
		return this.companyRepository.create(company);
	}
	/**
	 * Metodo che sovrascrive l'update
	 * @param company l'azienda
	 * 
	 * @return ritorna la company aggiornata
	 */
	@Transactional
	@Override
	public Company update(Company company) {
		return this.companyRepository.update(company);
	}
	/**
	 * Metodo che sovrascrive il delete
	 * @param company l'azienda
	 * 
	 * @return ritorna la company aggiornata dopo la delete
	 */
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

	/**
	 * Metodo getter e setter
	 */
	@Autowired
	public void setCompanyRepository(CompanyDao companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	@Autowired
	public void setjobOfferRepository(JobOfferDao jobOfferRepository) {
		this.jobOfferRepository = jobOfferRepository;
	}
}
