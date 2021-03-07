package survey.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import survey.model.statistic.MultipleChoiceQRS;
import survey.model.statistic.QuestionResultSummary;
import survey.model.statistic.RankingQRS;
import survey.model.statistic.RatingQRS;
import survey.model.statistic.ResponseGroup;
import survey.model.statistic.TextQRS;
import survey.model.statistic.dao.QuestionResultSummaryDao;
import survey.model.statistic.dao.ResponseGroupDao;
import survey.model.survey.Question;
import survey.model.survey.dao.QuestionDao;
import survey.util.Views;

@RestController
@RequestMapping("/api/questions")
@JsonView(Views.Internal.class)
public class QuestionController {

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private QuestionResultSummaryDao qResultSummaryDao;

	@Autowired
	private ResponseGroupDao resGroupDao;

	@GetMapping
	@JsonView(Views.SurveyQuestion.class)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Question> getQuestion() {

		return questionDao.getAllQuestions();
	}

	@GetMapping("/{questionId}")
	@JsonView(Views.Internal.class)
	public Question getQuestion(@PathVariable Long questionId) {

		return questionDao.getQuestion(questionId);
	}

	@GetMapping("/{questionId}/resultSummary")
	@JsonView(Views.Public.class)
	public QuestionResultSummary getQuestionResultSummary(@PathVariable Long questionId) {

		Question question = questionDao.getQuestion(questionId);

		System.out.println(questionId);
		System.out.println(question.getId());

		QuestionResultSummary qResultSummary =
				qResultSummaryDao.getQuestionResultSummaryByQuestionId(question.getId());



		if (qResultSummary != null) {
			System.out.println(qResultSummary.getId());
			qResultSummary.updateResultSummary();
		}

		else {
			System.out.println("not available");
			switch (question.getDecriminatorValue()) {
				case "MULTIPLE_CHOICE": {
					qResultSummary =
							new MultipleChoiceQRS(question.getQuestionSection().getSurvey(), question);
				}
					break;

				case "RANKING": {
					qResultSummary = new RankingQRS(question.getQuestionSection().getSurvey(), question);
				}
					break;

				case "RATING": {
					qResultSummary = new RatingQRS(question.getQuestionSection().getSurvey(), question);
				}
					break;

				case "TEXT": {
					qResultSummary = new TextQRS(question.getQuestionSection().getSurvey(), question);
				}
					break;

				default:
					break;
			}
		}

		if (qResultSummary != null) {
			qResultSummary = qResultSummaryDao.saveQuestionResultSummary(qResultSummary);
		}



		return qResultSummary;

	}


	@GetMapping("/qresultSummary/{questionId}")
	public QuestionResultSummary getQuestionResultSummary1(@PathVariable Long questionId) {

		return qResultSummaryDao.getQuestionResultSummary(questionId);
	}


	@DeleteMapping("/resGroup/{resGroupId}")
	public void removeResGroup(@PathVariable Long resGroupId) {

		ResponseGroup resGroup = resGroupDao.getResponseGroup(resGroupId);
		resGroup.getResponses().clear();
		resGroupDao.removeResponseGroup(resGroup);
	}


	@GetMapping("/{questionId}/charts")
	public Map<Object, Object> getChartRatingTesting(@PathVariable Long questionId) {

		return qResultSummaryDao.getRatingQuestionChartInfo(questionId);
	}

	@GetMapping("/{questionId}/resGroups/charts")
	public Map<Object, Object> getChartRatingTestingResGroup(@PathVariable Long questionId,
			@RequestBody Map<String, Object> resGroupInfo) {

		List<ResponseGroup> resGroups = new ArrayList<>();

		@SuppressWarnings("unchecked")
		List<Integer> resGroupIds = (List<Integer>) resGroupInfo.get("resGroupIds");

		if (resGroupIds != null) {
			for (int i = 0; i < resGroupIds.size(); i++) {
				resGroups.add(resGroupDao.getResponseGroup(Long.valueOf(resGroupIds.get(i))));
			}
		}

		Map<Object, Object> chartInfor =
				qResultSummaryDao.getRatingQuesChartInfoForResponseGroups(questionId, resGroups);

		return chartInfor;
	}


}
