package jobworld.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.entities.Company;

/**
 * Classe del Dao dell'azienda.
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
	public Company create(String email, String password, String description) {
		Company company = new Company(email, password, description);
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
		this.delete(company);
	}

	@Override
	@Transactional(readOnly = true)
	public Company findbyId(long id) {
		return getSession().find(Company.class, id);
	}

	public List<Company> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
