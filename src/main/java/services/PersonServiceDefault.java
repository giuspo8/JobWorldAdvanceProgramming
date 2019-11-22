/**
 * Implementazione PersonService
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
package services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.dao.PersonDao;
import model.entities.Person;

@Transactional
@Service("personService")
public class PersonServiceDefault implements PersonService{
private PersonDao personRepository;
	


	@Transactional(readOnly=true)
	@Override
	public Person findById(Long id) {
		return this.personRepository.findById(id);
	}

	@Transactional(readOnly=true)
	public List<Person> findAll() {
		return this.personRepository.findAll();
	}

	@Transactional
	@Override
	public Person create(String firstName, String lastName) {
		return this.create(firstName, lastName);
	}

	@Transactional
	@Override
	public Person create(String email, String password, String description, String firstName, String secondName,
			LocalDate birthDate, String number, String interests) {
		return this.personRepository.create(email,password,description,firstName,secondName,birthDate,number,interests);

	}
	
	@Transactional
	@Override
	public Person update(Person person) {
		return this.personRepository.update(person);
	}

	@Transactional
	@Override
	public void delete(Person person) {
		this.personRepository.delete(person);
	}


	@Autowired
	public void setSingerRepository(PersonDao personRepository) {
		this.personRepository = personRepository;
	}
}
