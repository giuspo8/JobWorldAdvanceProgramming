package services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.dao.CompanyDao;
import model.entities.Company;

@Transactional
@Service("companyService")
public class CompanyServiceDefault {

private CompanyDao companyRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Company findById(Long id) {
		return this.companyRepository.findById(id);
	}

	@Transactional(readOnly=true)
	public List<Company> findAll() {
		return this.companyRepository.findAll();
	}

	@Transactional
	@Override
	public Company create(String firstName, String lastName) {
		return this.create(firstName, lastName, null);
	}

	@Transactional
	@Override
	public Company create(String firstName, String lastName, LocalDate birthDate) {
		return this.companyRepository.create(firstName, lastName, birthDate);

	}
	
	@Transactional
	@Override
	public Company update(Company company) {
		return this.companyRepository.update(company);
	}

	@Transactional
	@Override
	public void delete(Company company) {
		this.companyRepository.delete(company);
	}


	@Autowired
	public void setCompanyRepository(CompanyDao singerRepository) {
		this.companyRepository = singerRepository;
	}
}
