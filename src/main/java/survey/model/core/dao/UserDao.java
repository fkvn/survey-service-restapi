package survey.model.core.dao;

import java.util.List;

import survey.model.core.User;

public interface UserDao {

	public List<User> getUsers();

	public User getUser(long id);

	public User saveUser(User user);

	public void removeUser(long id);
	

}
