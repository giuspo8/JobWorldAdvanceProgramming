package services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import model.dao.CurriculumDao;

import model.entities.Curriculum;
import model.entities.Person;

@Transactional
@Service("curriculumService")
public class CurriculumServiceDefault implements CurriculumService {
private CurriculumDao curriculumRepository;
	
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
		return this.curriculumRepository.create(person,workExperience,education,personalSkills,additionalInfo);
	}

	@Transactional
	@Override
	public Curriculum create(String firstName, String lastName) {
		return this.create(firstName, lastName);

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
