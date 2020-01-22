package survey.model.question;

import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */


@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
public class MultipleChoiceQuestion extends Question {
  /**
   * Default serialVersionUID.
   * 
   */
  private static final long serialVersionUID = 1L;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "question_choices", joinColumns = @JoinColumn(name = "question_id"))
  @Column(name = "choice", columnDefinition = "varchar(3000)")
  @OrderColumn(name = "choice_index")
  private List<String> choices;


  @Column(name = "number_of_selections", columnDefinition = "integer default 4")
  private int numberOfSelections;


  public List<String> getChoices() {
    return choices;
  }


  public void setChoices(List<String> choices) {
    this.choices = choices;
  }


  public int getNumberOfSelections() {
    return numberOfSelections;
  }


  public void setNumberOfSelections(int numberOfSelections) {
    this.numberOfSelections = numberOfSelections;
  }

}
