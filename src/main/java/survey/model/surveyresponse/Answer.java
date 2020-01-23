package survey.model.surveyresponse;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

  @ManyToOne
  @JoinColumn(name = "answer_section_id", nullable = false)
  private AnswerSection section;

  @Column(name = "answer_index")
  private int index;

  @ManyToOne
  @JoinColumn(name = "question_id")
  private Question question;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AnswerSection getSection() {
    return section;
  }

  public void setSection(AnswerSection section) {
    this.section = section;
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
