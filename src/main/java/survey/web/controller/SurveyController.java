package survey.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import survey.model.core.dao.UserDao;
import survey.model.survey.QuestionSection;
import survey.model.survey.Survey;
import survey.model.survey.SurveyType;
import survey.model.survey.dao.QuestionSectionDao;
import survey.model.survey.dao.SurveyDao;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

	@Autowired
	private SurveyDao surveyDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private QuestionSectionDao questionSectionDao;

	// Survey
	
	@GetMapping
	public List<Survey> getSurveys() {

		return surveyDao.getAllSurveys();
	}

	@GetMapping("/opened")
	public List<Survey> getOpenSurvey() {

		return surveyDao.getOpenSurveys();
	}

	@GetMapping("/deleted")
	public List<Survey> getDeletedSurvey() {

		return surveyDao.getDeletedSurveys();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Long addSurvey(@RequestBody Survey survey) {

		survey.setAuthor(userDao.getUser(3));
		survey.setCreatedDate(new Date());

		if (survey.getType() == null) {
			survey.setType(SurveyType.ANONYMOUS);
		}

		if (survey.getQuestionSections() == null || survey.getQuestionSections().size() < 1) {
			List<QuestionSection> questionSections = new ArrayList<>();
			questionSections.add(questionSectionDao.saveQuestionSection(new QuestionSection()));
			survey.setQuestionSections(questionSections);
		}

		survey = surveyDao.saveSurvey(survey);
		return survey.getId();
	}

	@GetMapping("/{id}")
	public Survey getSurvey(@PathVariable Long id) {

		return surveyDao.getSurvey(id);
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Long editSurvey(@PathVariable Long id, @RequestBody Map<String, Object> surveyInfo) {

		Survey survey = surveyDao.getSurvey(id);

		for (String key : surveyInfo.keySet()) {
			switch (key) {
				case "name":
					survey.setName((String) surveyInfo.get(key));
					break;
				case "description":
					survey.setDescription((String) surveyInfo.get(key));
					break;
				case "publishDate":
					survey.setPublishDate((Calendar) surveyInfo.get(key));
					break;
				case "closeDate":
					survey.setCloseDate((Calendar) surveyInfo.get(key));
					break;
				case "deleted":
					survey.setDeleted((boolean) surveyInfo.get(key));
					break;
				default:
			}
		}

		survey = surveyDao.saveSurvey(survey);

		return survey.getId();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSurvey(@PathVariable Long id) {

		surveyDao.removeSurvey(id);
	}

	// Survey Section

	@GetMapping("/{surveyId/sections}")
	public List<QuestionSection> getSections(@PathVariable Long surveyId) {

		return questionSectionDao.getQuestionSection(surveyId);
	}
}
