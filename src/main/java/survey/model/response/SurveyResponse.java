package survey.model.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import survey.model.statistic.ResponseGroup;
import survey.model.survey.Survey;
import survey.model.survey.SurveyType;
import survey.util.Views;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@Table(name = "survey_responses")
@JsonPropertyOrder({ "id", "survey", "type", "date", "isDeleted" })
public class SurveyResponse implements Serializable {

	/**
	 * Default serialVersionUID.
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@JsonView(Views.Public.class)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonView(Views.Public.class)
	private Survey survey;


	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "response_id")
	@OrderColumn(name = "response_section_index")
	@JsonView(Views.Internal.class)
	private List<AnswerSection> answerSections;

	@ManyToMany(mappedBy = "responses")
	@JsonView(Views.Internal.class)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private List<ResponseGroup> responseGroup;

	@JsonView(Views.Public.class)
	private boolean isDeleted = false;

	@Column(nullable = false)
	@JsonView(Views.Public.class)
	private Date date;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Survey getSurvey() {

		return survey;
	}

	public void setSurvey(Survey survey) {

		this.survey = survey;
	}

	public List<AnswerSection> getAnswerSections() {

		return answerSections;
	}

	public void setAnswerSections(List<AnswerSection> answerSections) {

		this.answerSections = answerSections;
	}

	public Date getDate() {

		return date;
	}

	public void setDate(Date date) {

		this.date = date;
	}

	public boolean isDeleted() {

		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {

		this.isDeleted = isDeleted;
	}

	@JsonView(Views.Public.class)
	public SurveyType getType() {

		return this.getSurvey().getType();
	}

	
	@JsonView(Views.Public.class)
	public Integer getTotalResposeGroups() {
		return this.responseGroup.size();
	}



	public List<ResponseGroup> getResponseGroup() {

		return responseGroup;
	}

	public void setResponseGroup(List<ResponseGroup> responseGroup) {

		this.responseGroup = responseGroup;
	}
}
