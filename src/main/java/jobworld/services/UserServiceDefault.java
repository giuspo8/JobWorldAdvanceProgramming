package jobworld.services;

import java.util.List;

import jobworld.model.dao.UserDao;
import jobworld.model.entities.User;
import jobworld.model.entities.User.Role;

public class UserServiceDefault implements UserService {
	
	private UserDao userRepository;

	@Override
	public User create(String email, String password, String description, String image, Role role) {
		return this.userRepository.create(email, password, description, image, role);
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
