package survey.model.surveyresponse;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import survey.model.core.File;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@DiscriminatorValue("TEXT")
public class TextAnswer extends Answer {

  /**
   * Default serialVersionUID.
   * 
   */
  private static final long serialVersionUID = 1L;

  private String text;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "attachment_id")
  private File attachment;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public File getAttachment() {
    return attachment;
  }

  public void setAttachment(File attachment) {
    this.attachment = attachment;
  }

}
