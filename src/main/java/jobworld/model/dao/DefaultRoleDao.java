package jobworld.model.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.entities.Curriculum;
import jobworld.model.entities.Person;
import jobworld.model.entities.Role;
import jobworld.model.entities.Role.TypeRole;

@Transactional
@Repository("roleDao")
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

	@Override
	@Transactional
	public Role getRoleByTypeRole(TypeRole name) {
		return getSession().createQuery("from Role r where r.name=:id", Role.class)
				.setParameter("id", name)
				.getSingleResult();
		
	}

}
