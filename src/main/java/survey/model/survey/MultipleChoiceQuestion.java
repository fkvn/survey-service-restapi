package survey.model.survey;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;

import com.fasterxml.jackson.annotation.JsonView;

import survey.util.Views;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */


@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
@JsonView(Views.Public.class)
public class MultipleChoiceQuestion extends Question {

	/**
	 * Default serialVersionUID.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "question_choices", joinColumns = @JoinColumn(name = "question_id"))
	@Column(name = "choice", columnDefinition = "varchar(3000)")
	@OrderColumn(name = "choice_index")
	@JsonView(Views.Public.class)
	private List<String> choices;

	@Column(name = "min_selections", nullable = false, columnDefinition = "int default 1")
//	@JsonView(Views.Public.class)
	private int minSelections = 1;

	@Column(name = "max_selections", nullable = false, columnDefinition = "int default 1")
//	@JsonView(Views.Public.class)
	private int maxSelections = 1;

	public List<String> getChoices() {

		return choices;
	}

	public void setChoices(List<String> choices) {

		this.choices = choices;
	}


	public int getMinSelections() {

		return minSelections;
	}


	public void setMinSelections(int minSelections) {

		this.minSelections = minSelections;
	}


	public int getMaxSelections() {

		return maxSelections;
	}


	public void setMaxSelections(int maxSelections) {

		this.maxSelections = maxSelections;
	}

	@Override
	public void updateQuestion(Question question) {
		this.setDescription(question.getDescription());
		this.setChoices(((MultipleChoiceQuestion) question).getChoices());
		this.setMaxSelections(((MultipleChoiceQuestion) question).getMaxSelections());
		this.setMinSelections(((MultipleChoiceQuestion) question).getMinSelections());
	}

}
