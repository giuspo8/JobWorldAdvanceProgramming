package jobworld.model.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.entities.JobOffer;
import jobworld.model.entities.Person;


/**
 * Implementazione dell'interfaccia PersonDao
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Transactional
@Repository("personDao")
public class DefaultPersonDao extends DefaultDao implements PersonDao {
	
	@Autowired
	JobOfferDao jobOfferDao;


	@Override
	@Transactional
	public Person create(Person person) {
		this.getSession().save(person);
		return person;
	}

	@Override
	@Transactional
	public Person update(Person person) {
		Person merged = (Person) this.getSession().merge(person);
		return merged;
	}

	@Override
	@Transactional
	public void delete(Person person) {
		this.getSession().delete(person);
	}

	@Override
	@Transactional
	public Person findById(long id) {
		return getSession().find(Person.class, id);
	}

	@Override
	@Transactional
	public List<Person> findAll() {
		return getSession().createQuery("from Person p", Person.class).getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Person findbyUserId(String id_user) {
		return getSession().createQuery("from Person p where p.user.email=:id", Person.class)
				.setParameter("id", id_user).getSingleResult();
	}

	@Override
	@Transactional
	public void unApplyAll(JobOffer jobOffer) {	
		List<Long> personId=getSession().createQuery("select p.id from Person p join p.candidacies c where c.id=:jobOfferid",Long.class)
				.setParameter("jobOfferid", jobOffer.getId()).getResultList();
		for(Long pId:personId) {
			Person p=findById(pId);
			p.getCandidacies().remove(jobOffer);
			update(p);
		}		
	}

	@Override
	@Transactional
	public Person apply(Person person, JobOffer joboffer) {
		joboffer.getCandidancies().add(person);
		jobOfferDao.update(joboffer);
		person.getCandidacies().add(joboffer);
		return update(person);
	}

	@Override
	@Transactional
	public Person unapply(Person person, JobOffer joboffer) {
		joboffer.getCandidancies().remove(person);
		jobOfferDao.update(joboffer);
		person.getCandidacies().remove(joboffer);
		return update(person);
	}

	@Override
	@Transactional
	public boolean isInterested(JobOffer jobOffer) {
		return isInterested(jobOffer);
	}	
}
