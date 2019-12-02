package jobworld.services;

import java.util.List;

import jobworld.model.entities.User;
import jobworld.model.entities.User.Role;

public interface UserService {

	public User create(String email, String password, String description, String image, Role role);
	
	User update(User user);

	void delete(User user);

	User findById(long id);
	
	List<User> findAll();
	
	User findByMailandPassword(String email,String password);

}
