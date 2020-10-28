package survey.model.survey.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import survey.model.survey.Question;
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

	@Override
	 @Transactional
	public QuestionSection moveQuestionInSections(Long surveyId, Long sectionId, int oldIndex,
			int newIndex) {

		QuestionSection section = entityManager
				.createQuery("from QuestionSection where id = :sectionId and survey.id = :surveyId",
						QuestionSection.class)
				.setParameter("surveyId", surveyId).setParameter("sectionId", sectionId).getSingleResult();

		List<Question> questions = section.getQuestions();

//		System.out.println("===== before moved =======");
//		questions.forEach(question -> {
//			Question q = (Question) question;
//			System.out.println(q.getQuestionIndex() + " - " + q.getId());
//		});

		List<Question> afterMovedQuestions = new ArrayList<>();
		for (int i = 0; i < questions.size(); i++) {
			afterMovedQuestions.add(null);
		}

		// System.out.println(afterMovedQuestions.size());

		int currentValueIndex = 0;
		for (int i = 0; i < questions.size(); i++) {
			if (i == oldIndex || i == newIndex) {
				if (i == oldIndex) {
					if (currentValueIndex == oldIndex) {
						currentValueIndex = currentValueIndex + 1;
					}
					afterMovedQuestions.set(i, questions.get(currentValueIndex++));
				}
				// newIndex
				else {
					afterMovedQuestions.set(i, questions.get(oldIndex));
				}
			} else {
				if (currentValueIndex == oldIndex) {
					currentValueIndex = currentValueIndex + 1;
				}
				afterMovedQuestions.set(i, questions.get(currentValueIndex++));
			}
		}

		section.setQuestions(afterMovedQuestions);
//
//		System.out.println("===== after moved =======");
//		questions = section.getQuestions();
//		questions.forEach(question -> {
//			Question q = (Question) question;
//			System.out.println(q.getQuestionIndex() + " -" + q.getId());
//		});

//		return null;
		 return entityManager.merge(section);

	}

}
