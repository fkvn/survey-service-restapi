package survey.model.response;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import survey.model.survey.Question;


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
public abstract class Answer implements Serializable {

	/**
	 * Default serialVersionUID.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "answer_index")
//	@JsonIgnore
	private int index;

	@ManyToOne
	@JoinColumn(name = "question_id")
	@JsonIgnore
	private Question question;
	
	
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

	public int getIndex() {

		return index;
	}

	public void setIndex(int index) {

		this.index = index;
	}

	public Question getQuestion() {

		return question;
	}

	public void setQuestion(Question question) {

		this.question = question;
	}
}
