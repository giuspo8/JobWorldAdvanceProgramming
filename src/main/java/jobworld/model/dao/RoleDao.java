package jobworld.model.dao;

import org.hibernate.Session;

import jobworld.model.entities.Role;

public interface RoleDao {
	Session getSession();
	public void setSession(Session session);

	Role create(String name);
	
	Role update(Role role);
	
	void delete(Role role);
}
