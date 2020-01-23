package survey.model.survey;

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
@DiscriminatorValue("RANKING")
public class RankingQuestion extends Question {
  /**
   * Default serialVersionUID.
   * 
   */
  private static final long serialVersionUID = 1L;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "question_ranking_choices",
      joinColumns = @JoinColumn(name = "question_id"))
  @Column(name = "ranking_choice", columnDefinition = "varchar(3000)")
  @OrderColumn(name = "ranking_choice_index")
  private List<String> rankingChoices;


  public List<String> getRankingChoices() {
    return rankingChoices;
  }


  public void setRankingChoices(List<String> rankingChoices) {
    this.rankingChoices = rankingChoices;
  }

}
