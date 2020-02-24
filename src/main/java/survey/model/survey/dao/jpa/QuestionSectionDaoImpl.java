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
	public List<QuestionSection> getQuestionSections(Long surveyId) {

		return entityManager
				.createQuery("from QuestionSection where survey_id = :surveyId", QuestionSection.class)
				.setParameter("surveyId", surveyId).getResultList();
	}

	@Override
	public QuestionSection getQuestionSection(Long id) {

		return entityManager.find(QuestionSection.class, id);
	}

	@Override
	@Transactional
	public QuestionSection saveQuestionSection(QuestionSection questionSection) {

		return entityManager.merge(questionSection);
	}

	@Override
	@Transactional
	public void removeQuestionSection(Long surveyId, Long id) {

		Survey survey = entityManager.find(Survey.class, surveyId);
		QuestionSection questionSection = entityManager.find(QuestionSection.class, id);
		survey.getQuestionSections().remove(questionSection);
		entityManager.remove(questionSection);

	}

	@Override
	@Transactional
	public void removeQuestionSection(Long id) {

		entityManager.remove(entityManager.find(QuestionSection.class, id));
	}

}
