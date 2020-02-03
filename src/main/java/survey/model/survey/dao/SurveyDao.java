package survey.model.survey.dao;

import java.util.List;

import survey.model.survey.Survey;

public interface SurveyDao {

	public List<Survey> getSurveys();

	public Survey getSurvey(long id);

	public Survey saveSurvey(Survey survey);

	public void removeSurvey(long id);
}
