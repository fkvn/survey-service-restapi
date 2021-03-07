package survey.model.response.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import survey.model.core.File;
import survey.model.response.SurveyResponse;

public interface SurveyResponseDao {

	List<SurveyResponse> getAllResponses();

	List<SurveyResponse> getSurveyResponsesAll(Long surveyId);

	List<SurveyResponse> getSurveyResponses(Long surveyId);

	List<SurveyResponse> getSurveyResponsesByQuestionId(Long questionId);

	List<SurveyResponse> getSurveyResponsesByQuestionDesc(String desc);

	List<SurveyResponse> getSurveyResponsesByQuestionAnswer(Long questionId, String answer);

	List<SurveyResponse> getSurveyResponsesByQuestionAnswer(String questionDesc, String answer);

	List<SurveyResponse> getSurveyResponsesByYear(Long surveyId, Integer year);
	
	List<Integer> getResponseYears(Long surveyId);
	
	Map<Integer, List<SurveyResponse>> getResponseGroupByYearsOfSurvey(@PathVariable Long surveyId);
	Map<Integer, List<SurveyResponse>> getSurveyResponsesByYears(Long surveyId);

	SurveyResponse getResponse(Long id);

	SurveyResponse saveResponse(SurveyResponse response);

	void removeResponse(Long id);
}
