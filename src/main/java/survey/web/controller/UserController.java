package survey.web.controller;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import survey.model.core.User;
import survey.model.core.dao.UserDao;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserDao userDao;

	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<User> getUsers() {

		return userDao.getUsers();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long addUser(@RequestBody User user) {
		
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		user.setPassword(hashed);

		user = userDao.saveUser(user);

		return user.getId();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public User getUser(@PathVariable long id) {

		return userDao.getUser(id);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable long id) {

		userDao.removeUser(id);
	}

}
