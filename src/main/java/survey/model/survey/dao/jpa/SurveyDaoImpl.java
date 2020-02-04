package survey.model.survey.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import survey.model.survey.Survey;
import survey.model.survey.dao.SurveyDao;

@Repository
public class SurveyDaoImpl implements SurveyDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Survey> getAllSurveys() {

		return entityManager.createQuery("from Survey", Survey.class).getResultList();
	}

	@Override
	public List<Survey> getOpenSurveys() {

		return entityManager.createQuery("from Survey where deleted = :deleted", Survey.class)
				.setParameter("deleted", false).getResultList();
	}

	@Override
	public List<Survey> getDeletedSurveys() {

		return entityManager.createQuery("from Survey where deleted = :deleted", Survey.class)
				.setParameter("deleted", true).getResultList();
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



}
