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

	/**
	 * Metodo che sovrascrive il create della persona
	 * @param person la persona
	 * 
	 * @return person
	 */
	@Override
	@Transactional
	public Person create(Person person) {
		this.getSession().save(person);
		return person;
	}
	/**
	 * Metodo che sovrascrive l'update della persona
	 * @param person la persona
	 * 
	 * @return merged
	 */
	@Override
	@Transactional
	public Person update(Person person) {
		Person merged = (Person) this.getSession().merge(person);
		return merged;
	}
	/**
	 * Metodo che sovrascrive il delete della persona
	 * @param person la persona
	 * 
	 * 
	 */
	@Override
	@Transactional
	public void delete(Person person) {
		this.getSession().delete(person);
	}
	/**
	 * Metodo che sovrascrive il findById, restituisce la persona in base all'id
	 * @param person la persona
	 * 
	 * @return person
	 */
	@Override
	@Transactional
	public Person findById(long id) {
		return getSession().find(Person.class, id);
	}
	/**
	 * Metodo che sovrascrive il findAll, restituisce tutte le persone
	 *
	 * 
	 * @return person
	 */
	@Override
	@Transactional
	public List<Person> findAll() {
		return getSession().createQuery("from Person p", Person.class).getResultList();
	}
	/**
	 * Metodo che sovrascrive findbyUserId, dall'id dell'utente trova la persona
	 * @param id_user l'utente
	 * 
	 * @return person
	 */
	@Override
	@Transactional(readOnly = true)
	public Person findbyUserId(String id_user) {
		return getSession().createQuery("from Person p where p.user.email=:id", Person.class)
				.setParameter("id", id_user).getSingleResult();
	}
	/**
	 * Metodo che sovrascrive l'unApplyAll cioè rimuove l'offerta di lavoro associata alla persona
	 * @param jobOffer l'offerta di lavoro
	 * 
	 * 
	 */
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

	/**
	 * Metodo che sovrascrive isInterested, restituisce gli interessati all'offerta di lavoro
	 * @param jobOffer  l'offerta di lavoro
	 * 
	 * @return gli interessati all'offerta di lavoro
	 */
	@Override
	@Transactional
	public boolean isInterested(JobOffer jobOffer) {
		return isInterested(jobOffer);
	}	
}
