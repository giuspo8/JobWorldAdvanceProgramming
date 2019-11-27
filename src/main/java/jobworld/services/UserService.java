package jobworld.services;

import java.util.List;

import jobworld.model.entities.Company;
import jobworld.model.entities.Person;
import jobworld.model.entities.User;
import jobworld.model.entities.User.Role;

public interface UserService {
	
	public User createCompanyUser(String email, String password, String description, String image, Role role,
			Company company);
	
	public User createPersonUser(String email, String password, String description, String image, Role role,
			Person person);
	
	User update(User user);

	void delete(User user);

	User findById(long id);
	
	List<User> findAll();

}
