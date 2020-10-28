package survey.model.response;


import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;

import survey.model.core.File;
import survey.model.survey.Question;
import survey.util.Views;


/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@Inheritance
@Table(name = "answer")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,
		property = "answerType")
@JsonSubTypes({ @Type(value = MultipleChoiceAnswer.class, name = "MULTIPLE_CHOICE"),
		@Type(value = RatingAnswer.class, name = "RATING"),
		@Type(value = RankingAnswer.class, name = "RANKING"),
		@Type(value = TextAnswer.class, name = "TEXT")

})
@DiscriminatorColumn(name = "answer_type")
@JsonPropertyOrder({ "id", "description"})
@JsonView(Views.Internal.class)
public abstract class Answer implements Serializable {

	/**
	 * Default serialVersionUID.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@JsonProperty("description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	@JsonIgnore
	private Question question;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private AnswerSection answerSection;
	
	@JsonProperty("answerIndex")
	@JsonView(Views.Internal.class)
	public int getAnswerIndex() {
		return answerSection.getAnswers().indexOf(this);
	}
	
	@JsonView(Views.Internal.class)
	public List<File> getAttachments() {
		return question.getAttachments();
	}


	@Transient
	@JsonIgnore
	public String getDecriminatorValue() {

		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Question getQuestion() {

		return question;
	}

	public void setQuestion(Question question) {	

		this.question = question;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}
}
