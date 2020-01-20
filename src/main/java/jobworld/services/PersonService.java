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
import jobworld.model.entities.User;
public interface PersonService {
List<Person> findAll();
	
Person findById(Long id);
	
Person create(String firstName, String secondName,LocalDate birthDate, String number, String interests,User user);
	
Person update(Person person);

Person apply(Person person, JobOffer joboffer);

Person unapply(Person person, JobOffer joboffer);

Person findbyUserId(String id);
	
void delete(Person contact);

void unapplyAll(JobOffer joboffer);


}
