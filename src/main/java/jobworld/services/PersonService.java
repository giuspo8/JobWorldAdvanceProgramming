/**
 * Interfaccia PersonService
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

import jobworld.model.entities.Person;
public interface PersonService {
List<Person> findAll();
	
Person findById(Long id);
	
Person create(String email, String password, String description, String image, String firstName, String secondName,
		LocalDate birthDate, String number, String interests, boolean roleAdmin);
	
Person update(Person person);
	
	void delete(Person contact);

}
