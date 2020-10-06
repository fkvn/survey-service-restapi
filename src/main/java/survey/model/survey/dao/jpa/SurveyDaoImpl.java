package survey.model.survey.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import survey.model.core.User;
import survey.model.survey.Question;
import survey.model.survey.QuestionSection;
import survey.model.survey.Survey;
import survey.model.survey.dao.SurveyDao;

@Repository
public class SurveyDaoImpl implements SurveyDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Survey> getSurveys(User user) {

		return entityManager.createQuery("from Survey where author.id =: userId", Survey.class)
				.setParameter("userId", user.getId()).getResultList();
	}

	@Override
	public List<Survey> getOpenSurveys() {

		return entityManager
				.createQuery("from Survey where closed = :closed and publishDate != null", Survey.class)
				.setParameter("closed", false).getResultList();
	}

	@Override
	public List<Survey> getClosedSurveys() {

		return entityManager.createQuery("from Survey where closed = :closed", Survey.class)
				.setParameter("closed", true).getResultList();
	}

	@Override
	public Survey getSurvey(long id) {

		return entityManager.find(Survey.class, id);
	}

	@Override
	@Transactional
	public Survey saveSurvey(Survey survey) {

		return entityManager.merge(survey);
	}

	@Override
	@Transactional
	public void removeSurvey(long id) {

		Survey survey = entityManager.find(Survey.class, id);
		entityManager.remove(survey);
	}

	@Override
	@Transactional
	public Survey moveSectionInSurvey(Long surveyId, int oldIndex, int newIndex) {

		Survey survey = entityManager.createQuery("from Survey where id = :surveyId", Survey.class)
				.setParameter("surveyId", surveyId).getSingleResult();

		List<QuestionSection> sections = survey.getQuestionSections();
		
		// System.out.println("===== before moved =======");
		// sections.forEach(section -> {
		// QuestionSection sec = (QuestionSection) section;
		// System.out.println(sec.getSectionIndex() + " - " + sec.getId());
		// });
		
		
		List<QuestionSection> afterMovedSections = new ArrayList<>();
		for (int i = 0; i < sections.size(); i++) {
			afterMovedSections.add(null);
		}

		// System.out.println(afterMovedQuestions.size());

		int currentValueIndex = 0;
		for (int i = 0; i < sections.size(); i++) {
			if (i == oldIndex || i == newIndex) {
				if (i == oldIndex) {
					if (currentValueIndex == oldIndex) {
						currentValueIndex = currentValueIndex + 1;
					}
					afterMovedSections.set(i, sections.get(currentValueIndex++));
				}
				// newIndex
				else {
					afterMovedSections.set(i, sections.get(oldIndex));
				}
			} else {
				if (currentValueIndex == oldIndex) {
					currentValueIndex = currentValueIndex + 1;
				}
				afterMovedSections.set(i, sections.get(currentValueIndex++));
			}
		}

		survey.setQuestionSections(afterMovedSections);
		
//		System.out.println("===== after moved =======");
//		sections = survey.getQuestionSections();
//		sections.forEach(section -> {
//			QuestionSection sec = (QuestionSection) section;
//			System.out.println(sec.getSectionIndex() + " - " + sec.getId());
//		});
		
		return entityManager.merge(survey);
	}

}
