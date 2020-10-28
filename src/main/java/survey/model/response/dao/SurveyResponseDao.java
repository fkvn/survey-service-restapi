package survey.model.response.dao;

import java.util.List;

import survey.model.response.SurveyResponse;

public interface SurveyResponseDao {

	List<SurveyResponse> getSurveyResponsesAll(Long surveyId);
	
	List<SurveyResponse> getSurveyResponses(Long surveyId);
	
	SurveyResponse getResponse(Long id);
	
	SurveyResponse saveResponse(SurveyResponse response);
	
	void removeResponse(Long id);
}
