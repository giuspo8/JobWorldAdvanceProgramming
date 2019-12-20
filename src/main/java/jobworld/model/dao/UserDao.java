package jobworld.model.dao;

import java.util.List;

import org.hibernate.Session;

import jobworld.model.entities.User;


public interface UserDao {
	
	Session getSession();
	
	public void setSession(Session session);
	
	public User create(String email, String password, String description, String image);
	
	User update(User user);

	void delete(User user);

	User findByEmail(String email);
	
	List<User> findAll();
	
	public String encryptPassword(String password);
	
	User findByMailandPassword(String email, String password);

}
