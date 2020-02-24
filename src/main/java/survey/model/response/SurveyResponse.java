package survey.model.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import survey.model.survey.Survey;
import survey.util.Views;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@Table(name = "survey_responses")
public class SurveyResponse implements Serializable {
  /**
   * Default serialVersionUID.
   * 
   */
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  @JsonIdentityReference(alwaysAsId = true)
  private Survey survey;

  @OneToMany(cascade = CascadeType.MERGE)
  @JoinColumn(name = "response_id")
  @OrderColumn(name = "response_section_index")
  private List<AnswerSection> answerSections;
  
	@Column(nullable = false)
	@JsonView(Views.Public.class)
	private Date date;
	
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Survey getSurvey() {
    return survey;
  }

  public void setSurvey(Survey survey) {
    this.survey = survey;
  }

  public List<AnswerSection> getAnswerSections() {
    return answerSections;
  }

  public void setAnswerSections(List<AnswerSection> answerSections) {
    this.answerSections = answerSections;
  }

	public Date getDate() {

		return date;
	}

	public void setDate(Date date) {

		this.date = date;
	}
}
