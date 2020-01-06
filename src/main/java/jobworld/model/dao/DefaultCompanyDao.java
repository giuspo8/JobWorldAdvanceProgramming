package jobworld.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.entities.Company;

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
	/**
	 * Metodo che sovrascrive il create della company
	 * @param company  l'azienda
	 * 
	 * @return company
	 */

	@Override
	public Company create(Company company) {
		this.getSession().save(company);
		return company;
	}
	
	/**
	 * Metodo che sovrascrive l'update della company
	 * @param company  l'azienda
	 * 
	 * @return merged
	 */
	@Override
	public Company update(Company company) {
		Company merged = (Company) this.getSession().merge(company);
		return merged;
	}
	
	/**
	 * Metodo che sovrascrive la delete della company
	 * @param company  l'azienda
	 * 
	 *
	 */
	@Override
	public void delete(Company company) {
		this.getSession().delete(company);
	}
	
	/**
	 * Metodo che sovrascrive il findbyId della company
	 * @param id  id dell'azienda
	 * 
	 * @return id
	 */
	@Override
	public Company findbyId(long id) {
		return getSession().find(Company.class, id);
	}
	
	/**
	 * Metodo che sovrascrive il findbyUserId della company, data un email restituisce la compagnia associata
	 * @param email  l'email
	 * 
	 * @return company
	 */
	@Override
	public Company findbyUserId(String email) {
		return getSession().createQuery("from Company c where c.user.id=:id", Company.class)
				.setParameter("id", email).getSingleResult();
	}
	
	/**
	 * Metodo che sovrascrive il findAll della company, restituisce tutte le company
	 * 
	 * 
	 * @return tutte le company
	 */
	@Override
	public List<Company> findAll() {
		return getSession().createQuery("from Company c", Company.class)
				.getResultList();
	}

}
