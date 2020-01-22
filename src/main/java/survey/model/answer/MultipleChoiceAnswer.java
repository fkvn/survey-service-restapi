package survey.model.answer;

import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */


@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
public class MultipleChoiceAnswer extends Answer {

  /**
   * Default serialVersionUID.
   * 
   */
  private static final long serialVersionUID = 1L;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "answer_selection", joinColumns = @JoinColumn(name = "answer_id"))
  @Column(name = "selection")
  private Set<Integer> selections;

  public Set<Integer> getSelections() {
    return selections;
  }

  public void setSelections(Set<Integer> selections) {
    this.selections = selections;
  }
}
