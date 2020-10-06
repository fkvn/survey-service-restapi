package survey.model.survey;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;

import survey.model.core.File;
import survey.model.response.Answer;
import survey.util.Views;


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
	@JsonView(Views.Public.class)
	private Long id;

	@Column(nullable = false, columnDefinition="TEXT")
	@JsonView(Views.Public.class)
	private String description;

	@JsonIgnore
	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
	@OrderBy("id asc")
	private List<Answer> answers;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private QuestionSection questionSection;

	@JsonProperty("questionIndex")
	@JsonView(Views.Public.class)
	public int getQuestionIndex() {
		return getQuestionSection().getQuestions().indexOf(this);
	}
	
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<File> attachments;

	@Transient
	@JsonView(Views.Internal.class)
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

	public List<File> getAttachments() {

		return attachments;
	}

	public void setAttachments(List<File> attachments) {

		this.attachments = attachments;
	}
	
	public abstract void updateQuestion(Question question, List<File> files);

	public QuestionSection getQuestionSection() {

		return questionSection;
	}

	public void setQuestionSection(QuestionSection questionSection) {

		this.questionSection = questionSection;
	}

}
