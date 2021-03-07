package survey.model.statistic;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import survey.model.survey.Question;
import survey.model.survey.Survey;
import survey.util.Views;


/**
 * @author kevinngo
 *
 */
@Entity
@Table(name = "question_result_summary")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,
		property = "questionType")
@JsonSubTypes({ @Type(value = MultipleChoiceQRS.class, name = "MULTIPLE_CHOICE"),
		@Type(value = RatingQRS.class, name = "RATING"),
		@Type(value = RankingQRS.class, name = "RANKING"), @Type(value = TextQRS.class, name = "TEXT")

})
@DiscriminatorColumn(name = "question_type")
@JsonView(Views.Public.class)
public abstract class QuestionResultSummary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuestionResultSummary() {}

	public QuestionResultSummary(Survey survey, Question question) {

		this.question = question;
		this.totalResponses = survey.getNumOfRespones();
		
	}

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	@JoinColumn(name="question_id", unique=true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private Question question;

	private int totalResponses = 0;
	
	public abstract void updateResultSummary();

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


	public int getTotalResponses() {

		return totalResponses;
	}


	public void setTotalResponses(int totalResponses) {

		this.totalResponses = totalResponses;
	}

}
