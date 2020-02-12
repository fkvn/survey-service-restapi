package survey.model.survey;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import survey.model.core.User;
import survey.util.Views;


/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@Table(name = "survey")
public class Survey implements Serializable {

	/**
	 * Default serialVersionUID.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@JsonView(Views.Public.class)
	private Long id;

	@Column(nullable = false)
	@JsonView(Views.Public.class)
	private String name;

	@Column(nullable = false)
	@JsonView(Views.Public.class)
	private SurveyType type;

	@Column(nullable = false)
	@JsonView(Views.Public.class)
	private String description;

	@Column(name = "publish_date")
	@JsonView(Views.Public.class)
	private Calendar publishDate;

	@Column(name = "close_date")
	@JsonView(Views.Public.class)
	private Calendar closeDate;

	@Column(name = "created_date", nullable = false)
	@JsonView(Views.Public.class)
	private Date createdDate;

	@Column(nullable = false)
	@JsonView(Views.Public.class)
	private boolean closed;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	@JsonView(Views.Public.class)
	private User author;

	@JsonView(Views.Internal.class)
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "survey_id")
	@OrderColumn(name = "section_index")
	private List<QuestionSection> questionSections;

	@JsonView(Views.Internal.class)
	@ManyToMany(fetch = FetchType.LAZY)
	private  Set<Question> questions;

	@JsonProperty("numberOfSections")
	@JsonView(Views.Public.class)
	@Transient
	public int getNumOfSections() {

		return questionSections.size();
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public SurveyType getType() {

		return type;
	}

	public void setType(SurveyType type) {

		this.type = type;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public Calendar getPublishDate() {

		return publishDate;
	}

	public void setPublishDate(Calendar publishDate) {

		this.publishDate = publishDate;
	}

	public Calendar getCloseDate() {

		return closeDate;
	}

	public void setCloseDate(Calendar closeDate) {

		this.closeDate = closeDate;
	}

	public Date getCreatedDate() {

		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {

		this.createdDate = createdDate;
	}

	public User getAuthor() {

		return author;
	}

	public void setAuthor(User author) {

		this.author = author;
	}

	public List<QuestionSection> getQuestionSections() {

		return questionSections;
	}

	public void setQuestionSections(List<QuestionSection> questionSections) {

		this.questionSections = questionSections;
	}

	public Set<Question> getQuestions() {

		return questions;
	}

	public void setQuestions(Set<Question> questions) {

		this.questions = questions;
	}

	public boolean isClosed() {

		return closed;
	}

	public void setClosed(boolean closed) {

		this.closed = closed;
	}

}
