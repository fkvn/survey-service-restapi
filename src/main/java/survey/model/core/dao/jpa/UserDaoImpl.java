package survey.model.core.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import survey.model.core.User;
import survey.model.core.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> getUsers() {

		return entityManager.createQuery("from User", User.class).getResultList();
	}

	@Override
	public User getUser(long id) {

		return entityManager.find(User.class, id);
	}

	@Override
	@Transactional
	public User saveUser(User user) {

		return entityManager.merge(user);
	}

	@Override
	@Transactional
	public void removeUser(long id) {

		User user = entityManager.find(User.class, id);
		entityManager.remove(user);
	}

}
