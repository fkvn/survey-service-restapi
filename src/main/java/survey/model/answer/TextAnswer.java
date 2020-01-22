package survey.model.answer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import survey.model.file.File;

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

  @OneToOne
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
