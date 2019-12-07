package jobworld.model.dao;

import java.time.LocalDate;
import java.util.List;

import jobworld.model.entities.Company;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.Person;
import jobworld.model.entities.User;

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

	Person create(String firstName, String secondName,LocalDate birthDate, String number,
	String interests,User user);

	Person update(Person person);

	void delete(Person person);

	Person findById(long id);
	
	List<Person> findAll();
	
	void unApplyAll(JobOffer jobOffer);
	
	Person findbyUserId(long id_user);
	
	

}
