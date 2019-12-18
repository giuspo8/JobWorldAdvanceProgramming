/**
 * Implementazione dell'interfaccia PersonService
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jobworld.model.dao.PersonDao;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.Person;
import jobworld.model.entities.User;

@Transactional
@Service("personService")
public class PersonServiceDefault implements PersonService{
private PersonDao personRepository;
//private JobOfferDao jobOfferRepository;
	


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
	public Person create(String firstName, String secondName,LocalDate birthDate, String number, String interests,User user) {
		return this.personRepository.create(firstName,secondName,birthDate,number,interests,user);

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
	public void setPersonRepository(PersonDao personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	@Transactional
	public Person apply(Person person, JobOffer joboffer) {
		return this.personRepository.apply(person, joboffer);
	}

	@Override
	@Transactional
	public void unapplyAll(JobOffer joboffer) {
		this.personRepository.unApplyAll(joboffer);
	}

	@Transactional(readOnly=true)
	@Override
	public Person findbyUserId(String id) {
		return this.personRepository.findbyUserId(id);
	}

	@Override
	@Transactional
	public Person unapply(Person person, JobOffer joboffer) {
		return this.personRepository.unapply(person, joboffer);
	}

	@Override
	@Transactional
	public boolean isInterested(JobOffer jobOffer) {
		return this.personRepository.isInterested(jobOffer);
	}
}
