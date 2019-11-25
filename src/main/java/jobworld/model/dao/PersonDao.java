package jobworld.model.dao;

import java.time.LocalDate;
import java.util.List;

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

	Person create(String email, String password, String description, String image, String firstName, String secondName,
			LocalDate birthDate, String number, String interests);

	Person update(Person person);

	void delete(Person person);

	Person findById(long id);
	
	List<Person> findAll();
}
