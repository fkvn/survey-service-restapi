package survey.model.survey;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import survey.model.surveyresponse.Answer;


/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@Inheritance
@Table(name = "question")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,
		property = "questionType")
@JsonSubTypes({ @Type(value = MultipleChoiceQuestion.class, name = "MULTIPLE_CHOICE"),
		@Type(value = RatingQuestion.class, name = "RATING"),
		@Type(value = RankingQuestion.class, name = "RANKING"),
		@Type(value = TextQuestion.class, name = "TEXT")

})
@DiscriminatorColumn(name = "question_type")
public abstract class Question implements Serializable {

	/**
	 * Default serialVersionUID.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String description;

	@OneToMany(mappedBy = "question")
	@OrderBy("id asc")
	private List<Answer> answers;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@OrderBy("id asc")
	private List<Survey> surveys;

	public Question() {

		this.answers = new ArrayList<>();
	}
	
	@Transient
	public String getDecriminatorValue() {

		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
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

	public List<Answer> getAnswers() {

		return answers;
	}

	public void setAnswers(List<Answer> answers) {

		this.answers = answers;
	}

	public List<Survey> getSurveys() {

		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {

		this.surveys = surveys;
	}

}
