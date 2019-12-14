package jobworld.model.dao;

import org.hibernate.Session;

import jobworld.model.entities.Role;
import jobworld.model.entities.Role.TypeRole;

public interface RoleDao {
	
	Session getSession();
	
	public void setSession(Session session);

	Role create(TypeRole name);
	
	Role update(Role role);
	
	void delete(Role role);
}
