package survey.model.response.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import survey.model.response.Answer;
import survey.model.response.dao.AnswerDao;

@Repository
public class AnswerDaoImpl implements AnswerDao {


	@PersistenceContext
	private EntityManager entityManager;


	@Override
	@Transactional
	public void removeAnswer(Long id) {

		entityManager.remove(entityManager.find(Answer.class, id));

	}

}
