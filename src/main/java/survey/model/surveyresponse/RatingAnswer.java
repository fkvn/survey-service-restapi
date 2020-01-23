package survey.model.surveyresponse;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@DiscriminatorValue("RATING")
public class RatingAnswer extends Answer {

  /**
   * Default serialVersionUID.
   * 
   */
  private static final long serialVersionUID = 1L;

  private Integer rating;

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }


}
