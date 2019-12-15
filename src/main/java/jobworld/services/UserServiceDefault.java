package jobworld.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import jobworld.model.dao.UserDao;
import jobworld.model.entities.Role;
import jobworld.model.entities.Role.TypeRole;
import jobworld.model.entities.User;
@Transactional
@Service("userService")
public class UserServiceDefault implements UserService,UserDetailsService {
	
	private UserDao userRepository;
	
	@Transactional(readOnly = true)
	  @Override
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	    User user = userRepository.findByEmail(email);
	    UserBuilder builder = null;
	    if (user != null) {
	      
	      // qui "mappiamo" uno User della nostra app in uno User di spring
	      builder = org.springframework.security.core.userdetails.User.withUsername(email);
	      builder.password(user.getPassword());
	            
	      String [] roles = new String[user.getRoles().size()];

	      int j = 0;
	      for (Role r : user.getRoles()) {
	    	  roles[j++] = r.getName().toString();
	      }
	      System.out.print(roles);
	      builder.roles(roles);
	    } else {
	      throw new UsernameNotFoundException("User not found.");
	    }
	    return builder.build();
	  }
	
	
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
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
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
