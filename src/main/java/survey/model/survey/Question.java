package survey.model.survey;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
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

}
