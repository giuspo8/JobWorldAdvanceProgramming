package jobworld.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.entities.Company;
import jobworld.model.entities.JobOffer;
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
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	JobOfferDao jobOfferDao;

	@Override
	public Company create(String name, User user) {
		Company company = new Company(name,user);
		user.setCompany(company);
		this.userDao.update(user);
		this.getSession().save(company);
		return company;
	}

	@Override
	public Company update(Company company) {
		Company merged = (Company) this.getSession().merge(company);
		return merged;
	}

	@Override
	public void delete(Company company) {
		User u=company.getUser();
		u.setCompany(null);
		userDao.delete(u);
		company.setUser(null);
		for (JobOffer j:company.getJobOffers()) 
		{
			jobOfferDao.delete(j);
		}
		//company.getJobOffers().clear();
		this.getSession().delete(company);
	}

	@Override
	public Company findbyId(long id) {
		return getSession().find(Company.class, id);
	}
	
	@Override
	public Company findbyUserId(long id_user) {
		return getSession().createQuery("from Company c where c.user.id=:id", Company.class)
				.setParameter("id", id_user).getSingleResult();
	}

	@Override
	public List<Company> findAll() {
		return getSession().createQuery("from Company c", Company.class)
				.getResultList();
	}

}
