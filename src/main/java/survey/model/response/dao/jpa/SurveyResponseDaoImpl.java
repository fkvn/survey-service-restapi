package survey.model.response.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import survey.model.response.SurveyResponse;
import survey.model.response.dao.SurveyResponseDao;

@Repository
public class SurveyResponseDaoImpl implements SurveyResponseDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<SurveyResponse> getSurveyResponses(Long surveyId) {

		return entityManager
				.createQuery("from SurveyResponse where survey.id =:surveyId", SurveyResponse.class)
				.setParameter("surveyId", surveyId).getResultList();
	}

	@Override
	public SurveyResponse getResponse(Long id) {

		return entityManager.find(SurveyResponse.class, id);
	}

	@Override
	@Transactional
	public SurveyResponse saveResponse(SurveyResponse response) {

		return entityManager.merge(response);
	}

	@Override
	@Transactional
	public void removeResponse(Long id) {
		SurveyResponse surveyResponse = entityManager.find(SurveyResponse.class, id);
		entityManager.remove(surveyResponse);
	}

}
