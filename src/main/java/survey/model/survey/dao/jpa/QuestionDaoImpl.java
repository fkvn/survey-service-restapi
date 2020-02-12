package survey.model.survey.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import survey.model.survey.Question;
import survey.model.survey.dao.QuestionDao;

@Repository
public class QuestionDaoImpl implements QuestionDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Question> getAllQuestions() {

		return entityManager.createQuery("from Question", Question.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getSectionQuestions(Long sectionId) {

		return (List<Question>) entityManager.createNativeQuery(
				"select q.* "
				+ "from question q join question_section_questions qS "
				+ "on q.id = qS.questions_id "
				+ "where qS.question_sections_id =:sectionId "
				+ "order by qS.question_index ",
				Question.class).setParameter("sectionId", sectionId).getResultList();
		// return null;
	}

	@Override
	public Question getQuestion(Long id) {

		return entityManager.find(Question.class, id);
	}


	@Override
	public Question getSectionQuestion(Long sectionId, Long questionId) {

		return (Question) entityManager.createNativeQuery(
				"select q.* "
				+ "from question q join question_section_questions qS "
				+ "on q.id = qS.questions_id "
				+ "where qS.question_sections_id =:sectionId and q.id = :questionId ",
				Question.class).setParameter("sectionId", sectionId).setParameter("questionId", questionId)
				.getSingleResult();
	}

	@Override
	@Transactional
	public Question saveQuestion(Question question) {

		return entityManager.merge(question);
	}

	@Override
	@Transactional
	public void removeQuestion(long id) {

		Question question = entityManager.find(Question.class, id);
		entityManager.remove(question);
	}

	@Override
	public Question isExist(String questionType, String questionDescription) {
		
		try {
			return entityManager
					.createQuery(
							"from Question where question_type = :questionType and description = :description",
							Question.class)
					.setParameter("description", questionDescription)
					.setParameter("questionType", questionType).getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}


}
