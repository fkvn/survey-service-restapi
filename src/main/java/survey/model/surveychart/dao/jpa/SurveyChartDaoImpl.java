package survey.model.surveychart.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import survey.model.surveychart.SurveyChart;
import survey.model.surveychart.dao.SurveyChartDao;

@Repository
public class SurveyChartDaoImpl implements SurveyChartDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<SurveyChart> getSurveyCharts() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SurveyChart> getSurveyChartsQuestion(Long questionId) {

		return entityManager
				.createQuery("from SurveyChart where question.id = :questionId", SurveyChart.class)
				.setParameter("questionId", questionId).getResultList();
	}
	
	@Override
	public SurveyChart getSurveyChart(Long id) {
		return entityManager.find(SurveyChart.class, id);
	}

	@Override
	@Transactional
	public SurveyChart saveSurveyChart(SurveyChart surveyChart) {

		return entityManager.merge(surveyChart);
	}

	@Override
	@Transactional
	public void removeSurveyChart(long id) {

		SurveyChart surveyChart = entityManager.find(SurveyChart.class, id);
		entityManager.remove(surveyChart);

	}





}
