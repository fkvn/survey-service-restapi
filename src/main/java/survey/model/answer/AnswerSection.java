package survey.model.answer;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import survey.model.survey.SurveyResponse;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@Table(name = "answer_section")
public class AnswerSection implements Serializable {
  /**
   * Default serialVersionUID.
   * 
   */

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "response_id", nullable = false)
  private SurveyResponse response;

  @Column(name = "response_section_index", nullable = false)
  private int index;

  @OneToMany(mappedBy = "section")
  @OrderColumn(name = "answer_index")
  private List<Answer> answers;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SurveyResponse getResponse() {
    return response;
  }

  public void setResponse(SurveyResponse response) {
    this.response = response;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }
}
