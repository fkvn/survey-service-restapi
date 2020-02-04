package survey.model.survey.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import survey.model.survey.QuestionSection;
import survey.model.survey.Survey;
import survey.model.survey.dao.QuestionSectionDao;

@Repository
public class QuestionSectionDaoImpl implements QuestionSectionDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<QuestionSection> getQuestionSection(Long surveyId) {

		Survey survey = entityManager.find(Survey.class, surveyId);
		return entityManager
				.createQuery("from QuestionSection where survey = :survey", QuestionSection.class)
				.setParameter("survey", survey).getResultList();
	}

	@Override
	public QuestionSection getQuestionSection(long id) {

		return entityManager.find(QuestionSection.class, id);
	}

	@Override
	@Transactional
	public QuestionSection saveQuestionSection(QuestionSection questionSection) {

		return entityManager.merge(questionSection);
	}

	@Override
	@Transactional
	public void removeQuestionSection(long id) {

		QuestionSection questionSection = entityManager.find(QuestionSection.class, id);
		entityManager.remove(questionSection);

	}

}
