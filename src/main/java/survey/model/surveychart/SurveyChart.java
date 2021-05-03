package survey.model.surveychart;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import survey.model.statistic.ResponseGroup;
import survey.model.survey.Question;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@Table(name = "survey_chart")
public class SurveyChart implements Serializable {
  /**
   * Default serialVersionUID.
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name = "Custom Chart";
  
  // This needs to be updated later for general chart
  // At the moment, we create fixed type of chart
  @Column(nullable = false)
  private String type = "qAdvChart";

  
  @ManyToMany
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  @JsonIdentityReference(alwaysAsId = true)
  private List<ResponseGroup> resGroups;
  
  @ManyToOne
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  @JsonIdentityReference(alwaysAsId = true)
  private Question question;

	public List<ResponseGroup> getResGroups() {

		return resGroups;
	}


	public void setResGroups(List<ResponseGroup> resGroups) {

		this.resGroups = resGroups;
	}


	public String getName() {

		return name;
	}


	public void setName(String name) {

		this.name = name;
	}


	public Question getQuestion() {

		return question;
	}


	public void setQuestion(Question question) {

		this.question = question;
	}


	public Long getId() {

		return id;
	}


	public void setId(Long id) {

		this.id = id;
	}

//  @ManyToOne
//  private String ownerId;
	
//@Column(name = "x_label")
//private String xLabel;
//
//@ElementCollection
//@CollectionTable(name = "survey_chart_xcoordinate", joinColumns = @JoinColumn(name = "chart_id"))
//@Column(name = "coordinate")
//@OrderColumn(name = "coordinate_order")
//private List<String> xCoordinates;
//
//@Column(name = "y_label")
//private String yLabel;
//
//@Column(name = "y_min")
//private Integer yMin;
//
//@Column(name = "y_max")
//private Integer yMax;

//
//	public String getOwnerId() {
//
//		return ownerId;
//	}
//
//	public void setOwnerId(String ownerId) {
//
//		this.ownerId = ownerId;
//	}

}
