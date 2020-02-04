package survey.model.survey;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@DiscriminatorValue("TEXT")
public class TextQuestion extends Question {

	/**
	 * Default serialVersionUID.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "text_length", columnDefinition = "integer default 20")
	private int textLength;

	@Column(name = "attachment_allowed", columnDefinition = "boolean default false", nullable = false)
	private boolean attachmentAllowed;

	public int getTextLength() {

		return textLength;
	}

	public void setTextLength(int textLength) {

		this.textLength = textLength;
	}

	public boolean isAttachmentAllowed() {

		return attachmentAllowed;
	}

	public void setAttachmentAllowed(boolean attachmentAllowed) {

		this.attachmentAllowed = attachmentAllowed;
	}


}
