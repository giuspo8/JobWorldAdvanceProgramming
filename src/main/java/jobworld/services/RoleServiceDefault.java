package jobworld.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.dao.RoleDao;
import jobworld.model.entities.Role;
import jobworld.model.entities.Role.TypeRole;

@Transactional
@Service("role")
public class RoleServiceDefault implements RoleService {

	private RoleDao roleRepository;
	
	/**
	 * Metodo ovverride di create
	 * @param name il nome
	 * 
	 * @return restituisce il repository dopo aver creato il ruolo aggiornato
	 */
	@Override
	public Role create(TypeRole name) {
		return this.roleRepository.create(name);
	}
	/**
	 * Metodo ovverride di update
	 * @param Role è il ruolo
	 * 
	 * @return restituisce il repository aggiornato
	 */
	@Override
	public Role update(Role role) {
		return this.roleRepository.update(role);
	}
	/**
	 * Metodo ovverride di delete
	 * @param Role è il ruolo
	 * 
	 * @return restituisce il repository aggiornato dopo la delete
	 */
	@Override
	public void delete(Role role) {
		this.roleRepository.delete(role);
		
	}
	/**
	 * Metodi set role
	 *
	 */
	@Autowired
	public void setRoleRepository(RoleDao roleRepository) {
		this.roleRepository = roleRepository;
	}
	/**
	 * Metodo ovverride di getRoleByTypeRole
	 * @param name è il nome
	 * 
	 * @return restituisce il ruolo tramite il nome
	 */
	@Override
	public Role getRoleByTypeRole(TypeRole name) {
		return this.roleRepository.getRoleByTypeRole(name);
	}

}
