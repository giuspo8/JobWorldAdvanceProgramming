package jobworld.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.entities.Company;
import jobworld.model.entities.User;

/**
 * Implementazione dell'interfaccia CompanyDao
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Transactional
@Repository("companyDao")
public class DefaultCompanyDao extends DefaultDao implements CompanyDao {

	@Override
	@Transactional
	public Company create(String name, User user) {
		Company company = new Company(name,user);
		this.getSession().save(company);
		return company;
	}

	@Override
	@Transactional
	public Company update(Company company) {
		Company merged = (Company) this.getSession().merge(company);
		return merged;
	}

	@Override
	@Transactional
	public void delete(Company company) {
		this.getSession().delete(company);
	}

	@Override
	@Transactional(readOnly = true)
	public Company findbyId(long id) {
		return getSession().find(Company.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Company> findAll() {
		return getSession().createQuery("from Company c", Company.class)
				.getResultList();
	}

}
