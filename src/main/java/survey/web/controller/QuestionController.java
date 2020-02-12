package survey.web.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import survey.model.survey.Question;
import survey.model.survey.QuestionSection;
import survey.model.survey.Survey;
import survey.model.survey.dao.QuestionDao;
import survey.model.survey.dao.QuestionSectionDao;
import survey.model.survey.dao.SurveyDao;
import survey.util.Views;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private QuestionSectionDao questionSectionDao;

	@Autowired
	private SurveyDao surveyDao;

	@GetMapping
	@JsonView(Views.SurveyQuestion.class)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Question> getQuestion() {

		return questionDao.getAllQuestions();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long addQuestion(@RequestBody Question question) {

		Question ifExistedQuestion =
				questionDao.isExist(question.getDecriminatorValue(), question.getDescription());
		if (ifExistedQuestion == null) {
			ifExistedQuestion = questionDao.saveQuestion(question);
		}

		return ifExistedQuestion.getId();
	}

	@GetMapping("/{questionId}")
	@JsonView(Views.SurveyQuestion.class)
	public Question getQuestion(@PathVariable Long questionId) {

		return questionDao.getQuestion(questionId);
	}

	@PutMapping("/{questionId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Long editQuestion(@PathVariable Long questionId, @RequestBody Question question) {

		Question existedQuestion = questionDao.getQuestion(questionId);

		existedQuestion.updateQuestion(question);
		existedQuestion = questionDao.saveQuestion(existedQuestion);

		return existedQuestion.getId();
	}

	@DeleteMapping("/{questionId}")
	public void deleteQuestion(@PathVariable Long questionId) {

		Question question = questionDao.getQuestion(questionId);
		Set<Survey> surveys = question.getSurveys();
		List<QuestionSection> questionSections = question.getQuestionSections();

		questionSections.forEach(qSection -> {
			qSection.getQuestions().remove(question);
			questionSectionDao.saveQuestionSection(qSection);
		});
		surveys.forEach(survey -> {
			survey.getQuestions().remove(question);
			surveyDao.saveSurvey(survey);

		});
		
		questionDao.removeQuestion(questionId);
	}
}
