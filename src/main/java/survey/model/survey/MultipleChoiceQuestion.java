package survey.model.survey;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */


@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
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
	private List<String> choices;

	@Column(name = "min_selections", nullable = false, columnDefinition = "int default 1")
	private int minSelections;

	@Column(name = "max_selections", nullable = false, columnDefinition = "int default 1")
	private int maxSelections;


	// @JsonCreator
	// public MultipleChoiceQuestion(@JsonProperty("minSelections") int minSelections,
	// @JsonProperty("maxSelections") int maxSelections,
	// @JsonProperty("choices") List<String> choices) {
	//
	// super();
	// this.minSelections = minSelections;
	// this.maxSelections = maxSelections;
	// this.choices = choices;
	// }


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

}
