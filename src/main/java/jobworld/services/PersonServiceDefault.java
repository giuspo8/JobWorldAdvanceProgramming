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

import jobworld.model.dao.JobOfferDao;
import jobworld.model.dao.PersonDao;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.Person;
import jobworld.model.entities.User;

@Transactional
@Service("personService")
public class PersonServiceDefault implements PersonService{
private PersonDao personRepository;
private JobOfferDao jobOfferRepository;

	


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
		Person person = new Person(firstName, secondName, birthDate, number, interests, user);
		user.setPerson(person);
		return this.personRepository.create(person);

	}
	
	@Transactional
	@Override
	public Person update(Person person) {
		return this.personRepository.update(person);
	}

	@Transactional
	@Override
	public void delete(Person person) {
		for (JobOffer j:person.getCandidacies()) {
			j.getCandidancies().remove(person);
		};
		person.getCandidacies().clear();
		personRepository.delete(person);
	}


	@Autowired
	public void setPersonRepository(PersonDao personRepository) {
		this.personRepository = personRepository;
	}

	@Autowired
	public void setJobOfferRepository(JobOfferDao jobOfferRepository) {
		this.jobOfferRepository = jobOfferRepository;
	}

	@Override
	@Transactional
	public Person apply(Person person, JobOffer joboffer) {
		joboffer.getCandidancies().add(person);
		joboffer=jobOfferRepository.update(joboffer);
		person.getCandidacies().add(joboffer);
		return this.personRepository.update(person);
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
		joboffer.getCandidancies().remove(person);
		joboffer=jobOfferRepository.update(joboffer);
		person.getCandidacies().remove(joboffer);
		return this.personRepository.update(person);
	}

	@Override
	@Transactional
	public boolean isInterested(JobOffer jobOffer) {
		return this.personRepository.isInterested(jobOffer);
	}
}
