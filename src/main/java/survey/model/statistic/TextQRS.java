package survey.model.statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

import survey.model.response.Answer;
import survey.model.response.SurveyResponse;
import survey.model.response.TextAnswer;
import survey.model.survey.Question;
import survey.model.survey.Survey;
import survey.model.survey.TextQuestion;

@Entity
@DiscriminatorValue("TEXT")
public class TextQRS extends QuestionResultSummary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TextQRS() {

		// TODO Auto-generated constructor stub
	}

	public TextQRS(Survey survey, Question question) {

		super(survey, question);
		this.textResponses = getTextResponsesFromQuestion((TextQuestion) question);
	}

	@OneToMany(cascade = CascadeType.ALL)   // unidirectional
	@JoinTable(name = "textResponses", joinColumns = @JoinColumn(name = "id"),
			inverseJoinColumns = @JoinColumn(name = "response_group_id"))
	@MapKeyJoinColumn(name = "String", nullable = true)
	private Map<String, ResponseGroup> textResponses;

	public Map<String, ResponseGroup> getTextResponsesFromQuestion(TextQuestion question) {

		Map<String, ResponseGroup> textResponses = new HashMap<>();

		String[] answerTypes = { "answered", "skipped" };

		for (int i = 0; i < answerTypes.length; i++) {
			ResponseGroup responseGroup = new ResponseGroup("textOption", answerTypes[i]);
			textResponses.put(answerTypes[i], responseGroup);
		}

		List<Answer> answers = question.getAnswers();


		for (int i = 0; i < answers.size(); i++) {
			TextAnswer ans = (TextAnswer) answers.get(i);
			SurveyResponse response = ans.getAnswerSection().getResponse();

			if (ans.getText().equals("")) {
				textResponses.get(answerTypes[1]).getResponses().add(response);
			} else {
				textResponses.get(answerTypes[0]).getResponses().add(response);
			}

		}

		return textResponses;
	}

	public Map<String, ResponseGroup> getTextResponses() {

		return textResponses;
	}

	public void setTextResponses(Map<String, ResponseGroup> textResponses) {

		this.textResponses = textResponses;
	}

	@Override
	public void updateResultSummary() {
//		this.getTextResponses().clear();
//		this.setTextResponses(getTextResponsesFromQuestion((TextQuestion) this.getQuestion()));
		this.getTextResponses().forEach((k, v) -> {
			v.getResponses().clear();
		});
		
		List<Answer> answers = this.getQuestion().getAnswers();
		String[] answerTypes = { "answered", "skipped" };

		for (int i = 0; i < answers.size(); i++) {
			TextAnswer ans = (TextAnswer) answers.get(i);
			SurveyResponse response = ans.getAnswerSection().getResponse();

			if (ans.getText().equals("")) {
				this.textResponses.get(answerTypes[1]).getResponses().add(response);
			} else {
				this.textResponses.get(answerTypes[0]).getResponses().add(response);
			}

		}
	}


}
