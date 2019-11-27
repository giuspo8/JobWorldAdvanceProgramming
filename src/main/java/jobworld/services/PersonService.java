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

import jobworld.model.entities.JobOffer;
import jobworld.model.entities.Person;
public interface PersonService {
List<Person> findAll();
	
Person findById(Long id);
	
Person create(String firstName, String secondName,LocalDate birthDate, String number, String interests);
	
Person update(Person person);

void apply(Person person, JobOffer joboffer);
	
void delete(Person contact);

}
