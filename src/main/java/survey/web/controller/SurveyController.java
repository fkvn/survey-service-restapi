package survey.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import survey.model.core.dao.UserDao;
import survey.model.survey.Survey;
import survey.model.survey.dao.SurveyDao;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {
	
	@Autowired
	private SurveyDao surveyDao;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping
	public List<Survey> getSurveys() {
		return surveyDao.getSurveys();
	}
	
	@PostMapping
	public Long addSurvey(@RequestBody Survey survey) {
		survey.setAuthor(userDao.getUser(3));
		survey.setCreatedDate(new Date());
		
		survey = surveyDao.saveSurvey(survey);
		return survey.getId();
	}
	
}
