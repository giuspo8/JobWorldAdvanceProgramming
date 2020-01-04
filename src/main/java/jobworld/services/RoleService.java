package jobworld.services;

import jobworld.model.entities.Role;
import jobworld.model.entities.Role.TypeRole;

public interface RoleService {
	Role create(TypeRole name);
	
	Role update(Role role);
	
	void delete(Role role);
	Role getRoleByTypeRole(TypeRole name);
}
