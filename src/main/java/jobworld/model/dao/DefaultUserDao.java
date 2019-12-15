package jobworld.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jobworld.model.entities.User;

@Transactional
@Repository("userDao") 
public class DefaultUserDao extends DefaultDao implements UserDao {

	@Autowired
	CompanyDao companyDao;
	
	@Autowired
	PersonDao personDao;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public User create(String email, String password, String description, String image) {
		User user = new User(email, password, description, description);
		this.getSession().save(user);
		return user;
	}

	@Override
	@Transactional
	public User update(User user) {
		User merged = (User) this.getSession().merge(user);
		return merged;
	}

	@Override
	@Transactional
	public void delete(User user) {
		this.getSession().delete(user);
	}

	@Override
	@Transactional
	public User findByEmail(String email) {
		return getSession().find(User.class, email);
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return getSession().createQuery("from User u", User.class).getResultList();
	}


	@Override
	public String encryptPassword(String password) {
		return this.passwordEncoder.encode(password);
	}
	
	
	
	@Override
	@Transactional
	public User findByMailandPassword(String email, String password) {
		return getSession().createQuery("from User u where u.email=:email and u.password=:password", User.class)
				.setParameter("email", email).setParameter("password",password).getSingleResult();
	}

}
