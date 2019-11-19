package model.dao;

import java.util.List;
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

	//ci restituisce tutte le offerte di lavoro filtrate per regione
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> findbyRegion(String region) {
		return getSession().createQuery("from JobOffer j where j.region='" + region + "'", JobOffer.class)
				.getResultList();
	}

	// ci restituisce la lista di tutte le offerte di lavoro presenti sul sito
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> findAll() {
		return getSession().createQuery("from JobOffer j", JobOffer.class).getResultList();
	}

	
	//ci restituisce la lista delle offerte di lavoro filtrate in base alla posizione (si può applicare a tutto)
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> filterByPosition(String keywords) {
		return getSession().createQuery("from JobOffer j where j.position like '%"+keywords+"%'", JobOffer.class).getResultList();
	}

	//ci restituisce la lista delle offerte di lavoro ordinata per data di pubblicazione (dalla più recente)
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> orderedByPublicationDate() {
		return getSession().createQuery("from JobOffer j order by j.publicationDate desc", JobOffer.class).getResultList();
	}

	//ci restituisce la lista delle offerte di lavoro filtrata per posizione e provincia
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> filterBypositionAndprovince(String position, String province) {
		return getSession()
				.createQuery("from JobOffer j where j.position like '%"+position+"%' and j.province='" + province + "'", JobOffer.class)
				.getResultList();
	}

}
