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
	
	@Transactional(readOnly=true)
	@Override
	public Curriculum findByPersonId(Person person) {
		return this.curriculumRepository.findByPersonId(person);
	}

	@Transactional(readOnly=true)
	public List<Curriculum> findAll() {
		return this.curriculumRepository.findAll();
	}

	@Transactional
	@Override
	public Curriculum create(Person person, String workExperience, String education, String personalSkills,
			String additionalInfo) {
		Curriculum curriculum=new Curriculum(person,workExperience,education,personalSkills,additionalInfo);
		return this.curriculumRepository.create(curriculum);
	}

	
	@Transactional
	@Override
	public Curriculum update(Curriculum curriculum) {
		return this.curriculumRepository.update(curriculum);
	}

	@Transactional
	@Override
	public void delete(Curriculum curriculum) {
		this.curriculumRepository.delete(curriculum);
	}


	@Autowired
	public void setCurriculumRepository(CurriculumDao curriculumRepository) {
		this.curriculumRepository = curriculumRepository;
	}
}
