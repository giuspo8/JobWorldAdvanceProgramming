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

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.dao.CompanyDao;
import jobworld.model.dao.JobOfferDao;
import jobworld.model.entities.Company;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.Person;
import jobworld.model.entities.JobOffer.Education;




@Transactional
@Service("jobofferService")
public class JobOfferServiceDefault implements JobOfferService{

private JobOfferDao jobofferRepository;
private CompanyDao companyRepository;	

/**
 * Metodo findAll
 * 
 * 
 * @return ritorna tutte le offerte di lavoro
 */
	@Transactional(readOnly=true)
	public List<JobOffer> findAll() {
		return this.jobofferRepository.findAll();
	}
	
	/**
	 * Metodo findbyId
	 * @param id id
	 * 
	 * @return ritorna tutte le offerte di lavoro in base all'id
	 */
	@Transactional(readOnly=true)
	public JobOffer findbyId(long id) {
		return this.jobofferRepository.findbyId(id);
	}
	/**
	 * Metodo findbyCompanyId
	 * @param companyid l'id dell'azienda
	 * 
	 * 
	 * @return ritorna tutte le offerte di lavoro in base all'id dell'azienda
	 */
	@Transactional(readOnly=true)
	public List<JobOffer> findbyCompanyId(long companyid) {
		return this.jobofferRepository.findbyCompanyId(companyid);
	}
	/**
	 * Metodo ovveride di create
	 * @param region regione
	 * @param privince provincia
	 * @param town città
	 * @param position posizione
	 * @param description descrizione
	 * 
	 * @return ritorna l'offerta di lavoro aggiornata dopo l'aggiunta
	 */
	@Transactional
	@Override
	public JobOffer create(String region, String province, String town, String position, String description,
			String contractType, Education minEducationLevel, String minExperience,LocalDate expiringDate, Company company) {
		JobOffer jobOffer = new JobOffer(region, province, town, position, description, contractType, minEducationLevel,
				minExperience,expiringDate, company);
		company.getJobOffers().add(jobOffer);
		jobOffer=jobofferRepository.create(jobOffer);
		company=companyRepository.update(company);
		return jobOffer;

	}
	/**
	 * Metodo update
	 * @param joboffer l'offerta di lavoro
	 * 
	 * @return aggiorna le offerte di lavoro
	 */
	@Transactional
	@Override
	public JobOffer update(JobOffer joboffer) {
		return this.jobofferRepository.update(joboffer);
	}
	/**
	 * Metodo override delete
	 * @param joboffer
	 * 
	 *
	 */
	@Transactional
	@Override
	public void delete(JobOffer joboffer) {
		for (Person p:joboffer.getCandidancies()) {
			p.getCandidacies().remove(joboffer);
		}
		joboffer.getCandidancies().clear();
		joboffer=jobofferRepository.update(joboffer);
		Company company=joboffer.getCompany();
		company.getJobOffers().remove(joboffer); //non serve fare update di company in quanto la relazione è mappata sul
		//lato joboffer (many) e quindi decade automaticamente con il delete
		jobofferRepository.delete(joboffer);
	}

	/**
	 * Metodi getters e setters
	 * 
	 * 
	 * 
	 */
	@Autowired
	public void setJobOfferRepository(JobOfferDao jobofferRepository) {
		this.jobofferRepository = jobofferRepository;
	}
	
	@Autowired
	public void setCompanyRepository(CompanyDao companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	/**
	 * Metodo ovverride filter, che filtra in base ai vari campi
	 * @param region  regione
	 * @param province  provincia 
	 * @param town  città
	 * @param position posizione
	 * @param contractType tipo di contratto
	 * 
	 * @return ritorna le offerte di lavoro filtrate
	 */
	@Override
	@Transactional
	public List<JobOffer> filter(String region, String province, String town, String position, String contractType,
			String minEducationLevel, String minExperience) {
		return this.jobofferRepository.filter(region, province, town, position, contractType, minEducationLevel, minExperience);
	}
	/**
	 * Metodo ovverride di getIntereset, restituisce gli interessati all'offerta di lavoro
	 * @param joboffer l'offerta di lavoro
	 * 
	 * @return restituisce gli interessati all'offerta di lavoro
	 */
	@Override
	@Transactional
	public Long getInterested(JobOffer jobOffer) {
		return this.jobofferRepository.getInterested(jobOffer);
	}

}
