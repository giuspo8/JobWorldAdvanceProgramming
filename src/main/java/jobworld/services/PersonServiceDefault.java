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

	

/**
 * Metodo ovverride di findByid
 * @param id l'id
 * 
 * @return restituisce la person in base all'id
 */
	@Transactional(readOnly=true)
	@Override
	public Person findById(Long id) {
		return this.personRepository.findById(id);
	}
	/**
	 * Metodo findAll
	 * 
	 * 
	 * @return restituisce tutte le persone
	 */
	@Transactional(readOnly=true)
	public List<Person> findAll() {
		return this.personRepository.findAll();
	}
	
	/**
	 * Metodo ovverride di create
	 * @param firstName nome 
	 * @param secondName cognome
	 * @param birthDate compleanno
	 * @param number numero di telefono
	 * @param interests interessi
	 * @param user utente
	 * 
	 * @return restituisce personRepository aggiornata dopo la create
	 */
	@Transactional
	@Override
	public Person create(String firstName, String secondName,LocalDate birthDate, String number, String interests,User user) {
		Person person = new Person(firstName, secondName, birthDate, number, interests, user);
		user.setPerson(person);
		return this.personRepository.create(person);

	}
	/**
	 * Metodo ovverride di update
	 * @param person persona
	 * 
	 * @return restituisce person repository aggiornata
	 */
	@Transactional
	@Override
	public Person update(Person person) {
		return this.personRepository.update(person);
	}

	/**
	 * Metodo ovverride di delete
	 * @param person persona
	 * 
	 * @return restituisce la personRepository aggiornata dopo la rimozione
	 */
	@Transactional
	@Override
	public void delete(Person person) {
		for (JobOffer j:person.getCandidacies()) {
			j.getCandidancies().remove(person);
			j=jobOfferRepository.update(j);
		};
		person.getCandidacies().clear();
		person=personRepository.update(person);
		personRepository.delete(person);
	}

	/**
	 * Metodi setters e getters
	 */
	@Autowired
	public void setPersonRepository(PersonDao personRepository) {
		this.personRepository = personRepository;
	}

	@Autowired
	public void setJobOfferRepository(JobOfferDao jobOfferRepository) {
		this.jobOfferRepository = jobOfferRepository;
	}

	
	/**
	 * Metodo ovverride di Apply, che associa la persona all'offerta di lavoro
	 * @param person persona
	 * @param joboffer offerta di lavoro
	 * 
	 * @return Associa la persona all'offerta di lavoro e restituisce i repository aggiornati
	 */
	@Override
	@Transactional
	public Person apply(Person person, JobOffer joboffer) {
		joboffer.getCandidancies().add(person);
		joboffer=jobOfferRepository.update(joboffer);
		person.getCandidacies().add(joboffer);
		return this.personRepository.update(person);
	}
	/**
	 * Metodo ovverride di unapplyAll
	 * @param joboffer offerta di lavoro
	 * 
	 * @return restituisce la repository dopo aver dissociato tutte le offerte di lavoro con le persone
	 */
	@Override
	@Transactional
	public void unapplyAll(JobOffer joboffer) {
		this.personRepository.unApplyAll(joboffer);
	}
	
	/**
	 * Metodo ovverride di findbyUserId
	 * @param id l'id
	 * 
	 * @return restituisce gli utenti tramite l'id
	 */
	@Transactional(readOnly=true)
	@Override
	public Person findbyUserId(String id) {
		return this.personRepository.findbyUserId(id);
	}
	
	/**
	 * Metodo ovverride di unapply
	 * @param person persona
	 * @param joboffer offerta di lavoro
	 * 
	 * @return restituisce il repository dopo aver dissociato la persona dall'offerta di lavoro
	 */
	@Override
	@Transactional
	public Person unapply(Person person, JobOffer joboffer) {
		Person p1=null;//creiamo un oggetto in cui mettere l'oggetto da eliminare in quanto non è possibile rimuovere un oggetto dal set durante l'iterazione
		for (Person p:joboffer.getCandidancies()) {
			if (p.equals(person))
				p1=p;//ci salviamo l'oggetto da eliminare
		}
		joboffer.getCandidancies().remove(p1);//lo eliminiamo a ciclo terminato
		joboffer=jobOfferRepository.update(joboffer);
		JobOffer j1=null;//idem a person
		for (JobOffer j:person.getCandidacies()) {
			if (j.equals(joboffer))
				j1=j;
		}
		person.getCandidacies().remove(j1);
		return this.personRepository.update(person);
	}

	@Override
	@Transactional
	public boolean isInterested(JobOffer jobOffer) {
		return this.personRepository.isInterested(jobOffer);
	}
}
