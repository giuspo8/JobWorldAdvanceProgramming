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
public class PersonServiceDefault {
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
		return this.create(firstName, lastName, null);
	}

	@Transactional
	@Override
	public Person create(String firstName, String lastName, LocalDate birthDate) {
		return this.personRepository.create(firstName, lastName, birthDate);

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
