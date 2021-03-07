package survey.model.survey.dao;

import java.util.List;

import survey.model.core.User;
import survey.model.survey.Survey;

public interface SurveyDao {

	public List<Survey> getOpenSurveys();

	public List<Survey> getSurveys(User user);

	public List<Survey> getClosedSurveys();

	public Survey getSurvey(long id);

	public Survey saveSurvey(Survey survey);
	
	public Survey moveSectionInSurvey(Long surveyId, int oldIndex, int newIndex);

	public void removeSurvey(long id);

}
