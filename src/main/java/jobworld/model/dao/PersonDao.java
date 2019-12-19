package jobworld.model.dao;


import java.util.List;

import org.hibernate.Session;

import jobworld.model.entities.JobOffer;
import jobworld.model.entities.Person;


/**
 * Interfaccia PersonDao
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
public interface PersonDao {
	
	Session getSession();
	
	public void setSession(Session session);

	Person create(Person person);
	
	Person apply(Person person, JobOffer joboffer);
	
	Person update(Person person);

	void delete(Person person);

	Person findById(long id);
	
	Person unapply(Person person, JobOffer joboffer);
	
	List<Person> findAll();
	
	void unApplyAll(JobOffer jobOffer);
	
	Person findbyUserId(String id_user);
	
	public boolean isInterested(JobOffer jobOffer);
}
