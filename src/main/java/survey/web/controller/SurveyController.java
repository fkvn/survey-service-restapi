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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import survey.model.core.dao.UserDao;
import survey.model.response.Answer;
import survey.model.response.SurveyResponse;
import survey.model.response.dao.AnswerSectionDao;
import survey.model.response.dao.SurveyResponseDao;
import survey.model.survey.Question;
import survey.model.survey.QuestionSection;
import survey.model.survey.Survey;
import survey.model.survey.SurveyType;
import survey.model.survey.dao.QuestionDao;
import survey.model.survey.dao.QuestionSectionDao;
import survey.model.survey.dao.SurveyDao;
import survey.util.Views;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

	@Autowired
	private SurveyDao surveyDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private QuestionSectionDao questionSectionDao;

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private SurveyResponseDao surveyResponseDao;

	@Autowired
	private AnswerSectionDao answerSectionDao;


	// Survey

	@JsonView(Views.Public.class)
	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Survey> getSurveys() {

		return surveyDao.getAllSurveys();
	}

	@GetMapping("/opened")
	@JsonView(Views.Public.class)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Survey> getOpenSurvey() {

		return surveyDao.getOpenSurveys();
	}

	@GetMapping("/closed")
	@JsonView(Views.Public.class)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Survey> getClosedSurvey() {

		return surveyDao.getClosedSurveys();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long addSurvey(@RequestBody Survey survey) {

		survey.setAuthor(userDao.getUser(3));
		survey.setCreatedDate(new Date());

		if (survey.getType() == null) {
			survey.setType(SurveyType.ANONYMOUS);
		}

		survey.setQuestionSections(new ArrayList<>());

		survey = surveyDao.saveSurvey(survey);

		return survey.getId();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Internal.class)
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
				case "closed":
					survey.setClosed((boolean) surveyInfo.get(key));
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

		Survey survey = surveyDao.getSurvey(id);

		survey.getQuestionSections().forEach(qSection -> {
			qSection.getQuestions().forEach(question -> questionDao.removeQuestion(question.getId()));
			questionSectionDao.removeQuestionSection(qSection.getId());;
		});

		surveyDao.removeSurvey(id);
	}

	// Survey > Section

	@JsonView(Views.Public.class)
	@GetMapping("/{surveyId}/sections")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<QuestionSection> getSections(@PathVariable Long surveyId) {

		return questionSectionDao.getQuestionSections(surveyId);
	}

	@PostMapping("/{surveyId}/sections")
	@ResponseStatus(HttpStatus.CREATED)
	public Long addSection(@PathVariable Long surveyId,
			@RequestBody QuestionSection questionSection) {

		Survey survey = surveyDao.getSurvey(surveyId);

		questionSection.setQuestions(new ArrayList<>());
		questionSection = questionSectionDao.saveQuestionSection(questionSection);

		survey.getQuestionSections().add(questionSection);
		survey = surveyDao.saveSurvey(survey);

		return survey.getQuestionSections().get(survey.getNumOfSections() - 1).getId();
	}

	@GetMapping("/{surveyId}/sections/{sectionId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Internal.class)
	public QuestionSection getSection(@PathVariable Long sectionId) {

		return questionSectionDao.getQuestionSection(sectionId);
	}

	@PatchMapping("/{surveyId}/sections/{sectionId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Long editSection(@PathVariable Long sectionId,
			@RequestBody Map<String, Object> questionSectionInfo) {

		QuestionSection questionSection = questionSectionDao.getQuestionSection(sectionId);

		for (String key : questionSectionInfo.keySet()) {
			switch (key) {

				case "description":
					questionSection.setDescription((String) questionSectionInfo.get(key));
					break;
				default:
			}
		}
		questionSection = questionSectionDao.saveQuestionSection(questionSection);
		return questionSection.getId();
	}

	@DeleteMapping("/{surveyId}/sections/{sectionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSection(@PathVariable Long surveyId, @PathVariable Long sectionId) {

		QuestionSection questionSection = questionSectionDao.getQuestionSection(sectionId);

		questionSection.getQuestions().forEach(question -> {
			questionDao.removeQuestion(question.getId());
		});

		questionSectionDao.removeQuestionSection(surveyId, sectionId);
	}

	// Survey > Section > Question

	@GetMapping("/{surveyId}/sections/{sectionId}/questions")
	@JsonView(Views.Public.class)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Question> getSectionQuestions(@PathVariable Long sectionId) {

		return questionDao.getSectionQuestions(sectionId);
	}

	@GetMapping("/{surveyId}/sections/{sectionId}/questions/{questionId}")
	@JsonView(Views.Public.class)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Question getSectionQuestion(@PathVariable Long sectionId, @PathVariable Long questionId) {

		return questionDao.getSectionQuestion(sectionId, questionId);
	}

	@PostMapping("/{surveyId}/sections/{sectionId}/questions")
	@ResponseStatus(HttpStatus.CREATED)
	public Long addQuestion(@PathVariable Long surveyId, @PathVariable Long sectionId,
			@RequestBody Question question) {

		QuestionSection questionSection = questionSectionDao.getQuestionSection(sectionId);

		question = questionDao.saveQuestion(question);

		questionSection.getQuestions().add(question);
		questionSectionDao.saveQuestionSection(questionSection);

		return question.getId();
	}

	@PutMapping("/{surveyId}/sections/{sectionId}/questions/{questionId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Long editQuestion(@PathVariable Long sectionId, @PathVariable Long questionId,
			@RequestBody Question question) {

		Question existedQuestion = questionDao.getQuestion(questionId);
		QuestionSection questionSection = questionSectionDao.getQuestionSection(sectionId);

		Long questionIndex = (long) questionSection.getQuestions().indexOf(existedQuestion);

		existedQuestion = questionDao.updateQuestion(sectionId, questionIndex, questionId, question);

		return existedQuestion.getId();
	}

	@DeleteMapping("/{surveyId}/sections/{sectionId}/questions/{questionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteQuestion(@PathVariable Long surveyId, @PathVariable Long sectionId,
			@PathVariable Long questionId) {

		Question question = questionDao.getQuestion(questionId);
		QuestionSection questionSection = questionSectionDao.getQuestionSection(sectionId);

		questionSection.getQuestions().remove(question);
		questionSectionDao.saveQuestionSection(questionSection);

		questionDao.removeQuestion(questionId);

	}

	// =================================================================

	// Survey > Response

	@GetMapping("/{surveyId}/responses")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<SurveyResponse> getSurveyResponses(@PathVariable Long surveyId) {

		return surveyResponseDao.getSurveyResponses(surveyId);
	}

	@PostMapping("/{surveyId}/responses")
	@ResponseStatus(HttpStatus.CREATED)
	public Long addSurveyResponse(@PathVariable Long surveyId, @RequestBody SurveyResponse response) {

		Survey survey = surveyDao.getSurvey(surveyId);

		response.setSurvey(survey);
		response.setDate(new Date());
		
//		response.setAnswerSections(new ArrayList<>());
		
		response = surveyResponseDao.saveResponse(response);
		
		for (int i = 0;  i < response.getAnswerSections().size(); i++) {
			int j = 0;
			
			for (Answer answer : response.getAnswerSections().get(i).getAnswers()) {
				answer.setIndex(j);
				answer.setQuestion(survey.getQuestionSections().get(i).getQuestions().get(j));
				j++;
			}
			
			response.getAnswerSections().get(i).setResponse(response);
			response.getAnswerSections().get(i).setIndex(i);
		}
		
		return surveyResponseDao.saveResponse(response).getId();
	}

	@GetMapping("/{surveyId}/responses/{responseId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public SurveyResponse getSurveyResponse(@PathVariable Long responseId) {

		return surveyResponseDao.getResponse(responseId);
	}

	@DeleteMapping("/{surveyId}/responses/{responseId}")
	public void deleteSurveyResponse(@PathVariable Long responseId) {

		surveyResponseDao.removeResponse(responseId);
	}
	
	

}
