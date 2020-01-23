package survey.model.surveyresponse;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import survey.model.survey.Survey;

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
  @JoinColumn(name = "survey_id", nullable = false)
  private Survey survey;

  @OneToMany
  @JoinColumn(name = "response_id")
  @OrderColumn(name = "response_section_index")
  private List<AnswerSection> answerSections;

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
}
