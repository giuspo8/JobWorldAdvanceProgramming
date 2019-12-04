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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	public void apply(Person person, JobOffer joboffer) {
		Set<JobOffer> jobOfferList=new HashSet<JobOffer>();
		jobOfferList.addAll(person.getCandidacies());
		person.getCandidacies().clear();
		this.personRepository.update(person);
		jobOfferList.add(joboffer);
		person.setCandidacies(jobOfferList);
		this.personRepository.update(person);
	}

	@Override
	@Transactional
	public void unapplyAll(JobOffer joboffer) {
		this.personRepository.unApplyAll(joboffer);
	}
}
