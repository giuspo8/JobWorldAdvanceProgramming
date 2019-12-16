package jobworld.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.dao.PersonDao;
import jobworld.model.dao.RoleDao;
import jobworld.model.entities.Role;
import jobworld.model.entities.Role.TypeRole;

@Transactional
@Service("role")
public class RoleServiceDefault implements RoleService {

	private RoleDao roleRepository;
	@Override
	public Role create(TypeRole name) {
		return this.roleRepository.create(name);
	}

	@Override
	public Role update(Role role) {
		return this.roleRepository.update(role);
	}

	@Override
	public void delete(Role role) {
		this.roleRepository.delete(role);
		
	}
	
	@Autowired
	public void setRoleRepository(RoleDao roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public Role getRoleByTypeRole(TypeRole name) {
		return this.roleRepository.getRoleByTypeRole(name);
	}

}
