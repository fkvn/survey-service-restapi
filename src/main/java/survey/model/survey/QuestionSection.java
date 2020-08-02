package survey.model.survey;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import survey.util.Views;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@Table(name = "question_section")
public class QuestionSection implements Serializable {

	/**
	 * Default serialVersionUID.
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	@JsonView(Views.Public.class)
	private Long id;

	@JsonView(Views.Public.class)
	private String description;

	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_section_id")
	@OrderColumn(name = "question_index")
//  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//  @JsonIdentityReference(alwaysAsId = true)
	@JsonView(Views.Internal.class)
	private List<Question> questions;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@Transient
	private Survey survey;

	@JsonProperty("sectionIndex")
	@Transient
	@JsonView(Views.Public.class)
	public int getSectionIndex() {

		return survey.getQuestionSections().indexOf(this);
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public List<Question> getQuestions() {

		return questions;
	}

	public void setQuestions(List<Question> questions) {

		this.questions = questions;
	}

}
