/**
 * implementazione dell'interfaccia CurriculumService
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
package jobworld.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import jobworld.model.dao.CurriculumDao;
import jobworld.model.dao.PersonDao;
import jobworld.model.entities.Curriculum;
import jobworld.model.entities.Person;

@Transactional
@Service("curriculumService")
public class CurriculumServiceDefault implements CurriculumService {
private CurriculumDao curriculumRepository;
private PersonDao personRepository;
	
/**
 * Metodo che sovrascrive il findByPersonId
 * @param person è la persona
 * 
 * @return ritorna il curriculum in base alla persona 
 */
	@Transactional(readOnly=true)
	@Override
	public Curriculum findByPersonId(Person person) {
		return this.curriculumRepository.findByPersonId(person);
	}
	/**
	 * Metodo findAll
	 * 
	 * @return ritorna tutti i curriculum
	 */
	@Transactional(readOnly=true)
	public List<Curriculum> findAll() {
		return this.curriculumRepository.findAll();
	}
	/**
	 * Metodo che sovrascrive il create
	 * @param curriculum è il curriculum
	 * 
	 * @return ritorna il curriculum aggiornato
	 */
	@Transactional
	@Override
	public Curriculum create(Curriculum curriculum) {
		Person p=curriculum.getPerson();
		p.setCurriculum(curriculum);
		curriculum=this.curriculumRepository.create(curriculum);
		p=personRepository.update(p);
		return curriculum;
	}

	/**
	 * Metodo che sovrascrive l'update
	 * @param curriculum curriculum
	 * 
	 * @return il curriculum aggiornato
	 */
	@Transactional
	@Override
	public Curriculum update(Curriculum curriculum) {
		return this.curriculumRepository.update(curriculum);
	}
	/**
	 * Metodo che sovrascrive la delete
	 * @param curriculum curriculum
	 * 
	 * @return il curriculum aggiornato dopo la delete
	 */
	@Transactional
	@Override
	public void delete(Curriculum curriculum) {
		Person p=curriculum.getPerson();
		p.setCurriculum(null);
		p=personRepository.update(p);
		curriculum.setPerson(null);
		curriculum=curriculumRepository.update(curriculum);
		curriculumRepository.delete(curriculum);
	}

	/**
	 * Metodi getters e setters
	 */
	@Autowired
	public void setCurriculumRepository(CurriculumDao curriculumRepository) {
		this.curriculumRepository = curriculumRepository;
	}


	@Autowired
	public void setPersonRepository(PersonDao personRepository) {
		this.personRepository = personRepository;
	}
}
