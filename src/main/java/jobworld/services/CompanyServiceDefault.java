/**
 * Implementazione dell'interfaccia CompanyService
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

import jobworld.model.dao.CompanyDao;
import jobworld.model.entities.Company;

@Transactional
@Service("companyService")
public class CompanyServiceDefault implements CompanyService{

private CompanyDao companyRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Company findbyId(Long id) {
		return this.companyRepository.findbyId(id);
	}

	@Transactional(readOnly=true)
	public List<Company> findAll() {
		return this.companyRepository.findAll();
	}


	@Transactional
	@Override
	public Company create(String name) {
		return this.companyRepository.create(name);

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
