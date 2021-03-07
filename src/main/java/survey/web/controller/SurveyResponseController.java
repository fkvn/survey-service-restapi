package survey.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import survey.model.response.SurveyResponse;
import survey.model.response.dao.SurveyResponseDao;
import survey.util.Views;

@RestController
@RequestMapping("/api/surveyResponses")
public class SurveyResponseController {

	@Autowired
	private SurveyResponseDao surveyResponseDao;

	// no need authorization
	@GetMapping
	@JsonView(Views.Public.class)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<SurveyResponse> getOpenSurvey() {

		return surveyResponseDao.getAllResponses();
	}


}
