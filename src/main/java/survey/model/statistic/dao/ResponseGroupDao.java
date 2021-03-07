package survey.model.statistic.dao;

import java.util.List;

import survey.model.statistic.ResponseGroup;

public interface ResponseGroupDao {
	
//	public ResponseGroup getResGroupByConditions(List<String> conditions);
//	public ResponseGroup getResGroupByConditions(Long resGroupId, String conditions);
//	public ResponseGroup getResGroupByConditions(List<SurveyResponse> surveyResponses, String conditions);
//
	
	
	public List<ResponseGroup> getResponseGroupsBySurvey(Long surveyId);
	
	public ResponseGroup getResponseGroup(String groupedBy, String groupedValue);
	public ResponseGroup getResponseGroup(Long resGroupId);
	
//	public ResponseGroup getResponseGroup(Long surveyId, String groupedBy, String groupedValue);
	
	public ResponseGroup saveResponseGroup(ResponseGroup responseGroup);
	
	public void removeResponseGroup(Long rgId);
	public void removeResponseGroup(ResponseGroup responseGroup);
}
