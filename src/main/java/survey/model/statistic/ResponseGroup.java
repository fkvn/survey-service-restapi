package survey.model.statistic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import survey.model.response.SurveyResponse;
import survey.util.Views;

@Entity
@Table(name = "response_group")
@JsonView(Views.Public.class)
public class ResponseGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResponseGroup() {}

	public ResponseGroup(String groupBy, String groupedValue, List<SurveyResponse> responses) {
		this.name = "";
		this.groupBy = groupBy;
		this.groupedValue = groupedValue;
		this.responses = responses;
	}

	public ResponseGroup(String groupBy, String groupedValue) {
		this.name = "";
		this.groupBy = groupBy;
		this.groupedValue = groupedValue;
		this.responses = new ArrayList<>();
	}
	
	public ResponseGroup(String name, ResponseGroupType groupType, String groupBy, String groupedValue, List<SurveyResponse> responses) {
		this.name = name;
		this.groupType = groupType;
		this.groupBy = groupBy;
		this.groupedValue = groupedValue;
		this.responses = responses;
	}

	public ResponseGroup(String name,ResponseGroupType groupType, String groupBy, String groupedValue) {
		this.name = name;
		this.groupType = groupType;
		this.groupBy = groupBy;
		this.groupedValue = groupedValue;
		this.responses = new ArrayList<>();
	}

	@Id
	@GeneratedValue
	private Long id;
	
	private String name = "";
	
	private ResponseGroupType groupType;

	private String groupBy = "";
	
	// groupBy="question"

	// groupedValue = "question_id: {id}"
	private String groupedValue = "";

	@ManyToMany
//	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//	@JsonIdentityReference(alwaysAsId = true)
//	@JsonView(Views.Public.class)
	private List<SurveyResponse> responses = new ArrayList<>();

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public List<SurveyResponse> getResponses() {

		return responses;
	}

	public void setResponses(List<SurveyResponse> responses) {

		this.responses = responses;
	}

	public String getGroupBy() {

		return groupBy;
	}

	public void setGroupBy(String groupBy) {

		this.groupBy = groupBy;
	}

	public String getGroupedValue() {

		return groupedValue;
	}

	public void setGroupedValue(String groupedValue) {

		this.groupedValue = groupedValue;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public ResponseGroupType getGroupType() {

		return groupType;
	}

	public void setGroupType(ResponseGroupType groupType) {

		this.groupType = groupType;
	}
}
