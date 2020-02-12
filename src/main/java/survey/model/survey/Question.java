package survey.model.survey;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import survey.model.surveyresponse.Answer;
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

	@Column(nullable = false)
	@JsonView(Views.Public.class)
	private String description;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonView(Views.SurveyQuestion.class)
	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
	@OrderBy("id asc")
	private List<Answer> answers;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToMany(mappedBy = "questions", fetch = FetchType.LAZY)
	@OrderBy("id asc")
	@JsonView(Views.SurveyQuestion.class)
	private Set<Survey> surveys;

	@JsonIgnore
	@ManyToMany(mappedBy = "questions", fetch = FetchType.LAZY)
	@OrderBy("id asc")
	private List<QuestionSection> questionSections;

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

	public Set<Survey> getSurveys() {

		return surveys;
	}

	public void setSurveys(Set<Survey> surveys) {

		this.surveys = surveys;
	}

	public List<QuestionSection> getQuestionSections() {

		return questionSections;
	}

	public void setQuestionSections(List<QuestionSection> questionSections) {

		this.questionSections = questionSections;
	}
	
	public abstract void updateQuestion(Question question);

}
