package survey.model.statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonView;

import ch.qos.logback.core.joran.action.Action;
import survey.model.response.Answer;
import survey.model.response.MultipleChoiceAnswer;
import survey.model.response.RankingAnswer;
import survey.model.response.SurveyResponse;
import survey.model.survey.MultipleChoiceQuestion;
import survey.model.survey.Question;
import survey.model.survey.RankingQuestion;
import survey.model.survey.Survey;
import survey.util.Views;

@Entity
@DiscriminatorValue("RANKING")
@JsonView(Views.Public.class)
public class RankingQRS extends QuestionResultSummary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RankingQRS() {

		// TODO Auto-generated constructor stub
	}

	public RankingQRS(Survey survey, Question question) {

		super(survey, question);
		this.rankingnResponses = getRankingResponsesFromQuestion((RankingQuestion) question);
	}

	@ManyToMany(cascade = CascadeType.ALL)   // unidirectional
	@JoinTable(name = "rankingResponses", joinColumns = @JoinColumn(name = "id"),
			inverseJoinColumns = @JoinColumn(name = "response_group_id"))
	@MapKeyJoinColumn(name = "String", nullable = true)
	private Map<String, ResponseGroup> rankingnResponses;

	public Map<String, ResponseGroup> getRankingResponsesFromQuestion(RankingQuestion question) {

		Map<String, ResponseGroup> rankingnResponses = new HashMap<>();

		for (int i = 0; i < question.getRankingChoices().size(); i++) {
			for (int j = 0; j < question.getRankingChoices().size(); j++) {
				String rankOption = (i + 1) + ":" + j;

				ResponseGroup responseGroup =
						new ResponseGroup("rankOption", (i + 1) + ":" + question.getRankingChoices().get(j));
				rankingnResponses.put(rankOption, responseGroup);

			}
		}

		List<Answer> answers = question.getAnswers();

		for (int i = 0; i < answers.size(); i++) {
			RankingAnswer ans = (RankingAnswer) answers.get(i);
			SurveyResponse response = ans.getAnswerSection().getResponse();

			ans.getSelectionRanks().forEach((rank, choiceIndex) -> {
				String rankOption = rank.toString() + ":" + choiceIndex.toString();
				rankingnResponses.get(rankOption).getResponses().add(response);
			});

		}

		return rankingnResponses;
	}

	public Map<String, ResponseGroup> getRankingnResponses() {

		return rankingnResponses;
	}

	public void setRankingnResponses(Map<String, ResponseGroup> rankingnResponses) {

		this.rankingnResponses = rankingnResponses;
	}

	@Override
	public void updateResultSummary() {
//		this.getRankingnResponses().clear();
//		this.setRankingnResponses(getRankingResponsesFromQuestion((RankingQuestion) this.getQuestion()));
		
		this.getRankingnResponses().forEach((k, v) -> {
			v.getResponses().clear();
		});
		
		List<Answer> answers = this.getQuestion().getAnswers();

		for (int i = 0; i < answers.size(); i++) {
			RankingAnswer ans = (RankingAnswer) answers.get(i);
			SurveyResponse response = ans.getAnswerSection().getResponse();

			ans.getSelectionRanks().forEach((rank, choiceIndex) -> {
				String rankOption = rank.toString() + ":" + choiceIndex.toString();
				this.rankingnResponses.get(rankOption).getResponses().add(response);
			});

		}
	}


}
