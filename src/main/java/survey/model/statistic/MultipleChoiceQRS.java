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

import com.fasterxml.jackson.annotation.JsonView;

import survey.model.response.Answer;
import survey.model.response.MultipleChoiceAnswer;
import survey.model.survey.MultipleChoiceQuestion;
import survey.model.survey.Question;
import survey.model.survey.Survey;
import survey.util.Views;

@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
@JsonView(Views.Public.class)
public class MultipleChoiceQRS extends QuestionResultSummary {

	public MultipleChoiceQRS() {}

	public MultipleChoiceQRS(Survey survey, Question question) {

		super(survey, question);
		this.selectionResponses = getSelectionResponsesFromQuestion((MultipleChoiceQuestion) question);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.ALL)   // unidirectional
  @JoinTable(name="sResponses",
             joinColumns=@JoinColumn(name="id"),
             inverseJoinColumns=@JoinColumn(name="response_group_id"))
  @MapKeyJoinColumn(name="Integer", nullable = true)
	private Map<Integer, ResponseGroup> selectionResponses;


	public Map<Integer, ResponseGroup> getSelectionResponses() {

		return selectionResponses;
	}

	public void setSelectionResponses(Map<Integer, ResponseGroup> selectionResponses) {

		this.selectionResponses = selectionResponses;
	}

	public Map<Integer, ResponseGroup> getSelectionResponsesFromQuestion(
			MultipleChoiceQuestion question) {

		Map<Integer, ResponseGroup> selectionResponses = new HashMap<>();

		for (int i = 0; i < question.getChoices().size(); i++) {
			ResponseGroup responseGroup = new ResponseGroup("mulChoiceSelection", question.getChoices().get(i));
			selectionResponses.put(i, responseGroup);
		}

		List<Answer> answers = question.getAnswers();


		for (int i = 0; i < answers.size(); i++) {
			MultipleChoiceAnswer ans = (MultipleChoiceAnswer) answers.get(i);
			ans.getSelections().forEach(selection -> {
				Integer index = (Integer) selection;
				selectionResponses.get(index).getResponses().add(ans.getAnswerSection().getResponse());
			});
		}

		return selectionResponses;
	}

	@Override
	public void updateResultSummary() {
		this.getSelectionResponses().forEach((k, v) -> {
			v.getResponses().clear();
		});
		
		List<Answer> answers = this.getQuestion().getAnswers();

		for (int i = 0; i < answers.size(); i++) {
			MultipleChoiceAnswer ans = (MultipleChoiceAnswer) answers.get(i);
			ans.getSelections().forEach(selection -> {
				Integer index = (Integer) selection;
				this.getSelectionResponses().get(index).getResponses().add(ans.getAnswerSection().getResponse());
			});
		}
//		this.setSelectionResponses(getSelectionResponsesFromQuestion((MultipleChoiceQuestion) this.getQuestion()));
	}

}
