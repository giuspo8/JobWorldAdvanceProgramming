package jobworld.model.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.entities.Role;
import jobworld.model.entities.Role.TypeRole;

@Transactional
@Repository
public class DefaultRoleDao extends DefaultDao implements RoleDao {

	@Override
	public Role create(TypeRole name) {
		Role r = new Role();
		r.setName(name);
		this.getSession().save(r);
		return r;
	}

	@Override
	public Role update(Role role) {
		return (Role)this.getSession().merge(role);
	}

	@Override
	public void delete(Role role) {
		this.getSession().delete(role);
	}

}
