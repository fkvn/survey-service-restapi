package survey.model.statistic.dao;

import java.util.List;
import java.util.Map;

import survey.model.statistic.QuestionResultSummary;
import survey.model.statistic.ResponseGroup;

public interface QuestionResultSummaryDao {
	
	public QuestionResultSummary getQuestionResultSummary(Long id);
	
	public QuestionResultSummary getQuestionResultSummaryByQuestionId(Long id);
	
	public QuestionResultSummary saveQuestionResultSummary(QuestionResultSummary qResultSummary);
	
	public void removeQuestionResultSummary(Long id);
	
	
	//
	
	public List<QuestionResultSummary> getAllQRSFromSurvey(long surveyId);
	
	public List<QuestionResultSummary> getAllQRSFromSection(Long sectionId);
	
	
	// 
	
	public Map<Object, Object> getRatingQuestionChartInfo(Long questionId);
	
	public Map<Object, Object> getRatingQuesChartInfoForResponseGroups(Long questionId, List<ResponseGroup> resGroups);

}
