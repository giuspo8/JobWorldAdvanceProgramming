package jobworld.services;

import java.util.List;

import jobworld.model.dao.UserDao;
import jobworld.model.entities.Company;
import jobworld.model.entities.Person;
import jobworld.model.entities.User;
import jobworld.model.entities.User.Role;

public class UserServiceDefault implements UserService {
	
	private UserDao userRepository;

	@Override
	public User createCompanyUser(String email, String password, String description, String image, Role role,
			Company company) {
		return this.userRepository.createCompanyUser(email, password, description, image, role, company);
	}

	@Override
	public User createPersonUser(String email, String password, String description, String image, Role role,
			Person person) {
		return this.userRepository.createPersonUser(email, password, description, image, role, person);
	}

	@Override
	public User update(User user) {
		return this.userRepository.update(user);
	}

	@Override
	public void delete(User user) {
		this.userRepository.delete(user);
	}

	@Override
	public User findById(long id) {
		return this.userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

}
