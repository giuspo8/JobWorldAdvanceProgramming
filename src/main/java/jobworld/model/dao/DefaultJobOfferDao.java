package jobworld.model.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.entities.Company;
import jobworld.model.entities.JobOffer;

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

	// ci restituisce tutte le offerte di lavoro filtrate per regione
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

	// ci restituisce la lista delle offerte di lavoro filtrate in base alla
	// posizione (si pu� applicare a tutto)
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> filterByPosition(String keywords) {
		return getSession().createQuery("from JobOffer j where j.position like '%" + keywords + "%'", JobOffer.class)
				.getResultList();
	}

	// ci restituisce la lista delle offerte di lavoro ordinata per data di
	// pubblicazione (dalla pi� recente)
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> orderedByPublicationDate() {
		return getSession().createQuery("from JobOffer j order by j.publicationDate desc", JobOffer.class)
				.getResultList();
	}

	// ci restituisce la lista delle offerte di lavoro filtrata per posizione e
	// provincia
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> filterBypositionAndprovince(String position, String province) {
		return getSession().createQuery(
				"from JobOffer j where j.position like '%" + position + "%' and j.province='" + province + "'",
				JobOffer.class).getResultList();
	}

	// filtro generale per jobOffer in base a posizione, dati geografici, tipo di
	// contratto
	// minimo titolo di studio richiesto e minima esperienza richiesta. quando i
	// campi vengono lasciati vuoti
	// viene valutato come AND TRUE
	@Override
	@Transactional
	public List<JobOffer> filter(String region, String province, String town, String position, String contractType,
			String minEducationLevel, String minExperience) {
		if (region == null)
			region = "";
		if (province == null)
			province = "";
		if (town == null)
			town = "";
		if (position == null)
			position = "";
		if (contractType == null)
			contractType = "";
		if (minEducationLevel == null)
			minEducationLevel = "";
		if (minExperience == null)
			minExperience = "";
		return getSession().createQuery(
				"from JobOffer j where j.position like '%" + position + "%'and j.region like '%" + region + "%'"
						+ "and j.province like '%" + province + "%' and j.town like '%" + town + "%' "
						+ "and j.contractType like '%" + contractType + "%' and j.minEducationLevel like '%"
						+ minEducationLevel + "%'" + "and j.minExperience like '%" + minExperience + "%'",
				JobOffer.class).getResultList();
	}

}
