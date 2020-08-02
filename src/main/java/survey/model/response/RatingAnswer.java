package survey.model.response;

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

  private Integer rating = 0;

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

}
