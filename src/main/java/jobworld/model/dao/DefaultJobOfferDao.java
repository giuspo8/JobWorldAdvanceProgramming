package jobworld.model.dao;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import jobworld.model.entities.JobOffer;

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
	

	@Override
	@Transactional
	public JobOffer create(JobOffer jobOffer) {
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
		this.getSession().delete(jobOffer);
	}
	
	// ci restituisce la lista di tutte le offerte di lavoro presenti sul sito
	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> findAll() {
		LocalDate actual = LocalDate.now();
		return getSession().createQuery("from JobOffer j where j.expiringDate >= :date order by j.publicationDate desc", JobOffer.class)
				.setParameter("date", actual).getResultList();
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
		LocalDate actual = LocalDate.now();
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
								+ " and j.expiringDate >= :date order by j.publicationDate desc",
				JobOffer.class)
				.setParameter("position", position).setParameter("region", region).setParameter("province", province)
				.setParameter("town", town).setParameter("contractType", contractType)
				.setParameter("minEducationLevel", minEducationLevel).setParameter("minExperience", minExperience)
				.setParameter("date", actual)
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
