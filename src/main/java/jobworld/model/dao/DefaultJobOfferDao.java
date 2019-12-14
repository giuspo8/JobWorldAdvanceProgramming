package jobworld.model.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.entities.Company;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.JobOffer.Education;
import jobworld.model.entities.Person;

/**
 * Implementazione dell'interfaccia JobOfferDao
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
	
	@Autowired
	CompanyDao companyDao;
	@Autowired
	PersonDao personDao;

	@Override
	@Transactional
	public JobOffer create(String region, String province, String town, String position, String description,
			String contractType, Education minEducationLevel, String minExperience, LocalDate expiringDate,Company company) {
		JobOffer jobOffer = new JobOffer(region, province, town, position, description, contractType, minEducationLevel,
				minExperience,expiringDate, company);
		company.getJobOffers().add(jobOffer);
		this.getSession().save(jobOffer);
		companyDao.update(company);
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
		this.getSession().delete(jobOffer);
	}
	
	// ci restituisce la lista di tutte le offerte di lavoro presenti sul sito
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> findAll() {
		return getSession().createQuery("from JobOffer j order by j.publicationDate desc", JobOffer.class).getResultList();
	}

	// ci restituisce tutte le offerte di lavoro filtrate per regione.
	//caso sottoinsieme del filtro generale
	/*
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> findbyRegion(String region) {
		return getSession().createQuery("from JobOffer j where j.region like concat('%',:regione,'%')", JobOffer.class)
				.setParameter("regione", region)
				.getResultList();
	}

	// ci restituisce la lista delle offerte di lavoro filtrate in base alla
	// posizione (si pu� applicare a tutto)
	//caso sottoinsieme del filtro generale
	
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> filterByPosition(String keywords) {
		return getSession().createQuery("from JobOffer j where j.position likeconcat('%',:parola,'%')", JobOffer.class)
				.setParameter("parola", keywords)
				.getResultList();
	}

	// ci restituisce la lista delle offerte di lavoro ordinata per data di
	// pubblicazione (dalla pi� recente)
	//caso sottoinsieme del filtro generale
	/*
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> orderedByPublicationDate() {
		return getSession().createQuery("from JobOffer j order by j.publicationDate desc", JobOffer.class)
				.getResultList();
	}

	// ci restituisce la lista delle offerte di lavoro filtrata per posizione e
	// provincia
	//caso sottoinsieme del filtro generale
	
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> filterBypositionAndprovince(String position, String province) {
		return getSession().createQuery(
				"from JobOffer j where j.position like concat('%',:position,'%') and j.province likeconcat('%',:province,'%') ",
				JobOffer.class)
				.setParameter("position",position).setParameter("province",province).getResultList();
	}
	*/

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
				"from JobOffer j where j.position like concat('%',:position,'%') and j.region like concat('%',:region,'%') "
						+ "and j.province like concat('%',:province,'%') and j.town like concat('%',:town,'%') "
						+ "and j.contractType like concat('%',:contractType,'%') and j.minEducationLevel like "
						+ "concat('%',:minEducationLevel,'%') and j.minExperience like concat('%',:minExperience,'%')"
								+ "order by j.publicationDate desc",
				JobOffer.class)
				.setParameter("position", position).setParameter("region", region).setParameter("province", province)
				.setParameter("town", town).setParameter("contractType", contractType)
				.setParameter("minEducationLevel", minEducationLevel).setParameter("minExperience", minExperience)
				.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public JobOffer findbyId(long id) {
		return getSession().find(JobOffer.class, id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> findbyCompanyId(long id) {
		return getSession().createQuery("from JobOffer j where j.company.id=:id_company", JobOffer.class)
				.setParameter("id_company", id).getResultList();
	}

	@Override
	public Long getInterested(JobOffer jobOffer) {
		return getSession().createQuery("select count(*) from Person p join p.candidacies c where c.id=:jid", Long.class)
				.setParameter("jid",jobOffer.getId()).getSingleResult();
	}

}
