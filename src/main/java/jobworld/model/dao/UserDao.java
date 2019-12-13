package jobworld.model.dao;

import java.util.List;
import jobworld.model.entities.User;


public interface UserDao {
	
	public User create(String email, String password, String description, String image);
	
	User update(User user);

	void delete(User user);

	User findById(long id);
	
	List<User> findAll();
	
	public String encryptPassword(String password);
	
	User findByMailandPassword(String email, String password);
	//ritorna l'utente associato con quella mail e password

}
