package jobworld.model.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.entities.Role;
import jobworld.model.entities.Role.TypeRole;
/**
 * Implementazione dell'interfaccia RoleDao
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Transactional
@Repository("roleDao")
public class DefaultRoleDao extends DefaultDao implements RoleDao {
	/**
	 * Metodo che definisce il create del ruolo
	 * @param name il nome del ruolo
	 * 
	 * @return r
	 */
	@Override
	public Role create(TypeRole name) {
		Role r = new Role();
		r.setName(name);
		this.getSession().save(r);
		return r;
	}
	/**
	 * Metodo che definisce l'update del ruolo
	 * @param role è il ruolo
	 * 
	 * @return il merge del ruolo
	 */
	@Override
	public Role update(Role role) {
		return (Role)this.getSession().merge(role);
	}
	/**
	 * Metodo che definisce l'update del ruolo
	 * @param role è il ruolo
	 * 
	 */
	@Override
	public void delete(Role role) {
		this.getSession().delete(role);
	}
	/**
	 * Metodo che definisce getRoleByTypeRole, restituisce il nome dal ruolo
	 * @param name 
	 * 
	 * @return ritorna l'id
	 */
	@Override
	@Transactional
	public Role getRoleByTypeRole(TypeRole name) {
		return getSession().createQuery("from Role r where r.name=:id", Role.class)
				.setParameter("id", name)
				.getSingleResult();
		
	}

}
