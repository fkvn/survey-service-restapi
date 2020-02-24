package survey.model.survey;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModelProperty;
import survey.model.core.User;
import survey.model.response.SurveyResponse;
import survey.util.Views;


/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@Table(name = "survey")
@JsonPropertyOrder({"id", "author","name", "type", "description", "createdDate", "publishDate", "closeDate", "closed", "numberOfSections"})
public class Survey implements Serializable {

	/**
	 * Default serialVersionUID.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@JsonView(Views.Public.class)
	@ApiModelProperty(hidden=true)
	private Long id;

	@Column(nullable = false)
	@JsonView(Views.Public.class)
	@ApiModelProperty(position=1, required = true)
	private String name;

	@Column(nullable = false)
	@JsonView(Views.Public.class)
	@ApiModelProperty(position=2)
	private SurveyType type;

	@Column(nullable = false)
	@JsonView(Views.Public.class)
	@ApiModelProperty(position=3)
	private String description;

	@Column(name = "publish_date")
	@JsonView(Views.Public.class)
	@ApiModelProperty(position=5)
	private Calendar publishDate;

	@Column(name = "close_date")
	@JsonView(Views.Public.class)
	@ApiModelProperty(position=6)
	private Calendar closeDate;

	@Column(name = "created_date", nullable = false)
	@JsonView(Views.Public.class)
	@ApiModelProperty(position=4)
	private Date createdDate;

	@Column(nullable = false)
	@JsonView(Views.Public.class)
	@ApiModelProperty(position=7)
	private boolean closed;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	@JsonView(Views.Public.class)
	@ApiModelProperty(hidden=true)
	private User author;

	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "survey_id")
	@OrderColumn(name = "section_index")
	@JsonView(Views.Internal.class)
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  @JsonIdentityReference(alwaysAsId = true)
	@ApiModelProperty(hidden=true)
	private List<QuestionSection> questionSections;
	
	@OneToMany(mappedBy="survey",fetch = FetchType.LAZY)
	@JsonIgnore
	@ApiModelProperty(hidden=true)
	private List<SurveyResponse> responses;

	@JsonProperty("numberOfSections")
	@JsonView(Views.Public.class)
	@ApiModelProperty(hidden=true)
	@Transient
	public int getNumOfSections() {

		return questionSections.size();
	}
	
	@JsonProperty("numberOfResponses")
	@JsonView(Views.Public.class)
	@ApiModelProperty(hidden=true)
	@Transient
	public int getNumOfRespones() {

		return responses.size();
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

	public boolean isClosed() {

		return closed;
	}

	public void setClosed(boolean closed) {

		this.closed = closed;
	}

	public List<SurveyResponse> getResponses() {

		return responses;
	}

	public void setResponses(List<SurveyResponse> responses) {

		this.responses = responses;
	}

}
