package survey.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import survey.exception.BadRequest;
// import survey.model.response.Answer;
import survey.model.response.SurveyResponse;
import survey.model.response.dao.SurveyResponseDao;
import survey.model.statistic.ResponseGroup;
import survey.model.statistic.ResponseGroupType;
import survey.model.statistic.dao.ResponseGroupDao;
import survey.util.Views;

@RestController
@RequestMapping("/api/responseGroups")
public class ResponseGroupController2 {

	@Autowired
	private ResponseGroupDao responseGroupDao;

	@Autowired
	private SurveyResponseDao surveyResponseDao;


	@GetMapping("/{resGroupId}")
	@JsonView(Views.Public.class)
	public ResponseGroup getResponseGroup(@PathVariable Long resGroupId) {

		return responseGroupDao.getResponseGroup(resGroupId);
	}

	@SuppressWarnings("unchecked")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(Views.Public.class)
	public Long addResponseGroup(@RequestBody Map<String, Object> resGroupInfo) {

		String groupBy = (String) resGroupInfo.get("groupBy");
		String groupedValue = (String) resGroupInfo.get("groupedValue");

		String name = (String) resGroupInfo.get("name");
		String groupType = (String) resGroupInfo.get("groupType");

		if (groupBy == null || groupedValue == null || name == null || groupType == null
				|| (groupType.equals("NAME") && groupedValue.split(";").length != 2)) {
			throw new BadRequest("Missing fields!!!");
		}

		if ((groupType.equals("NAME") && groupedValue.split(";").length == 2)
				&& !groupedValue.split(";")[1].equals(name)) {
			throw new BadRequest("groupedValue for name doesn't match with group name!!!");
		}

		ResponseGroup resGroup = null;

		try {
			resGroup = responseGroupDao.getResponseGroup(groupBy, groupedValue);
		} catch (Exception e) {
		} ;

		if (resGroup != null) {
			resGroup.getResponses().clear();
		} else {
			resGroup =
					new ResponseGroup(name, ResponseGroupType.valueOf(groupType), groupBy, groupedValue);
		}


		List<String> givenResponses = (List<String>) resGroupInfo.get("responses");;

		
		if (givenResponses == null) {
			givenResponses = new ArrayList<>();
		}

		List<SurveyResponse> responses = getResponsesForResGroup(groupBy, groupedValue, givenResponses);
		resGroup.setResponses(responses);

		resGroup = responseGroupDao.saveResponseGroup(resGroup);

		return resGroup.getId();
	}

	@GetMapping("/surveys/{surveyId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Public.class)
	public List<ResponseGroup> getResponsesBySurvey(@PathVariable Long surveyId) {

		List<ResponseGroup> resGroups = new ArrayList<>();

		resGroups = responseGroupDao.getResponseGroupsBySurvey(surveyId);

		return resGroups;
	}


	@GetMapping("/survey/{surveyId}/years")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Public.class)
	public Map<Integer, List<SurveyResponse>> getResponseGroupByYearsOfSurvey(
			@PathVariable Long surveyId) {

		Map<Integer, List<SurveyResponse>> responseByYears = new HashMap<>();

		List<Integer> years = surveyResponseDao.getResponseYears(surveyId);

		for (Integer year : years) {
			String groupedBy = "surveyId;year";
			String groupedValue = surveyId + ";" + year;

			responseByYears.put(year,
					responseGroupDao.getResponseGroup(groupedBy, groupedValue).getResponses());
		}

		return responseByYears;

	}

	@PostMapping("/survey/{surveyId}/years")
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(Views.Public.class)
	public List<Integer> addResponseGroupByYearsOfSurvey(@PathVariable Long surveyId,
			@RequestBody Map<String, Object> resGroupInfo) {

		List<Integer> years = surveyResponseDao.getResponseYears(surveyId);

		for (Integer year : years) {
			String groupedBy = "surveyId;year";
			String groupedValue = surveyId + ";" + year;
			ResponseGroup resGroup = null;

			String name = (String) resGroupInfo.get("name");
			ResponseGroupType groupType = ResponseGroupType.YEAR;

			if (groupedBy == null || groupedValue == null || name == null || groupType == null) {
				throw new BadRequest("Missing fields!!!");
			}

			try {
				resGroup = responseGroupDao.getResponseGroup(groupedBy, groupedValue);

			} catch (Exception e) {
			} ;

			if (resGroup != null) {
				resGroup.getResponses().clear();
			} else {
				resGroup = new ResponseGroup(name, groupType, groupedBy, groupedValue);
			}

			resGroup.setResponses(surveyResponseDao.getSurveyResponsesByYear(surveyId, year));
			resGroup = responseGroupDao.saveResponseGroup(resGroup);
		}

		return years;
	}

	@DeleteMapping("/{resGroupId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeResponseGroup(@PathVariable Long resGroupId) {

		responseGroupDao.removeResponseGroup(resGroupId);
	}


	// =========== helper functions =================

	public List<SurveyResponse> getResponsesForResGroup(String groupBy, String groupedValue,
			List<String> givenResponses) {

		List<SurveyResponse> responses = new ArrayList<>();

		switch (groupBy) {
			case "surveyId": {
				Long surveyId = Long.valueOf(groupedValue);
				responses = surveyResponseDao.getSurveyResponses(surveyId);
			}
				break;
			case "surveyId;name": {
				Long surveyId = Long.valueOf(groupedValue.split(";")[0]);
				for (String responseId : givenResponses) {
					SurveyResponse response = surveyResponseDao.getResponse(Long.valueOf(responseId));
					if (response.getSurvey().getId().equals(surveyId)) {
						responses.add(response);
					}
				}
			}
				break;
			case "surveyId;questionId":
			case "surveyId;questionId;mulChoiceSelectionIndex":
			case "surveyId;questionId;rating":
			case "surveyId;questionId;textAnswerType":
			case "surveyId;questionId;rank;rankChoiceIndex": {
				String[] groupByAttrs = groupBy.split(";");

				if (groupByAttrs.length == 2) {
					responses = surveyResponseDao
							.getSurveyResponsesByQuestionId(Long.valueOf(groupedValue.split(";")[1]));
				} else if (groupByAttrs.length == 3) {
					String[] groupedValues = groupedValue.split(";");
					responses = surveyResponseDao
							.getSurveyResponsesByQuestionAnswer(Long.valueOf(groupedValues[1]), groupedValues[2]);
				} else if (groupByAttrs.length == 4) {
					String[] groupedValues = groupedValue.split(";");
					responses = surveyResponseDao.getSurveyResponsesByQuestionAnswer(
							Long.valueOf(groupedValues[1]), groupedValues[2] + ";" + groupedValues[3]);
				}
			}
				break;
			case "questionDesc":
			case "questionDesc;mulChoiceSelectionIndex":
			case "questionDesc;rating":
			case "questionDesc;textAnswerType":
			case "questionDesc;rank;rankChoiceIndex": {
				String[] groupByAttrs = groupBy.split(";");

				if (groupByAttrs.length == 1) {
					responses = surveyResponseDao.getSurveyResponsesByQuestionId(Long.valueOf(groupedValue));
				} else if (groupByAttrs.length == 2) {
					String[] groupedValues = groupedValue.split(";");
					responses = surveyResponseDao
							.getSurveyResponsesByQuestionAnswer(Long.valueOf(groupedValues[0]), groupedValues[1]);
				} else if (groupByAttrs.length == 3) {
					String[] groupedValues = groupedValue.split(";");
					responses = surveyResponseDao.getSurveyResponsesByQuestionAnswer(
							Long.valueOf(groupedValues[0]), groupedValues[1] + ";" + groupedValues[2]);
				}

				String desc = groupedValue;
				responses = surveyResponseDao.getSurveyResponsesByQuestionDesc(desc);
			}
				break;
			case "surveyId;year": {
				String[] groupByAttrs = groupedValue.split(";");
				responses = surveyResponseDao.getSurveyResponsesByYear(Long.valueOf(groupByAttrs[0]),
						Integer.valueOf(groupByAttrs[1]));
			}
				break;
			default:
				break;
		}

		return responses;
	}
}
