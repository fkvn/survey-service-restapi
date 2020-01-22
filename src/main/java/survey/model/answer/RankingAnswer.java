package survey.model.answer;

import java.util.Map;
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
@DiscriminatorValue("RANKING")
public class RankingAnswer extends Answer {
  /**
   * Default serialVersionUID.
   * 
   */
  private static final long serialVersionUID = 1L;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "answer_selection", joinColumns = @JoinColumn(name = "answer_id"))
  @Column(name = "selection_rank")
  private Map<Integer, Integer> selectionRanks;

  public Map<Integer, Integer> getSelectionRanks() {
    return selectionRanks;
  }

  public void setSelectionRanks(Map<Integer, Integer> selectionRanks) {
    this.selectionRanks = selectionRanks;
  }
}
