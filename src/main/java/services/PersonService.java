package services;

import java.time.LocalDate;
import java.util.List;

import model.entities.Person;
public interface PersonService {
List<Person> findAll();
	
Person findById(Long id);
	
Person create(String string, String string2);
	
Person create(String string, String string2, LocalDate birthdate);
	
Person update(Person person);
	
	void delete(Person contact);

}
