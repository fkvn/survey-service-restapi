package survey.model.surveychart.dao;

import java.util.List;
import survey.model.surveychart.SurveyChart;

public interface SurveyChartDao {

	public List<SurveyChart> getSurveyCharts();

	public List<SurveyChart> getSurveyChartsQuestion(Long questionId);
	
	public SurveyChart getSurveyChart(Long id);

	public SurveyChart saveSurveyChart(SurveyChart surveyChart);

	public void removeSurveyChart(long id);
}
