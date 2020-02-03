package survey.model.survey.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import survey.model.survey.Survey;
import survey.model.survey.dao.SurveyDao;

@Repository
public class SurveyDaoImpl implements SurveyDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Survey> getSurveys() {

		return entityManager.createQuery("from Survey", Survey.class).getResultList();
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
