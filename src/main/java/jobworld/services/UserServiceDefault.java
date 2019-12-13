package jobworld.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import jobworld.model.dao.UserDao;
import jobworld.model.entities.User;
@Transactional
@Service("userService")
public class UserServiceDefault implements UserService {
	
	private UserDao userRepository;
	@Transactional
	@Override
	public User create(String email, String password, String description, String image) {
		return this.userRepository.create(email, password, description, image);
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
	@Autowired
	public void setUserRepository(UserDao userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findByMailandPassword(String email, String password) {
		return this.userRepository.findByMailandPassword(email, password);
	}

}
