package survey.model.survey;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonView;

import survey.model.core.File;
import survey.util.Views;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@DiscriminatorValue("TEXT")
@JsonView(Views.Public.class)
public class TextQuestion extends Question {

	/**
	 * Default serialVersionUID.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "text_length")
	@JsonView(Views.Public.class)
	private int textLength = -1;

	@Column(name = "attachment_allowed", columnDefinition = "boolean default false", nullable = false)
	@JsonView(Views.Public.class)
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

	@Override
	public void updateQuestion(Question question, List<File> files) {
		this.setDescription(question.getDescription());
		this.setTextLength(((TextQuestion) question).getTextLength());
		this.setAttachmentAllowed(((TextQuestion) question).isAttachmentAllowed());
		this.setAttachments(files);
	}


}
