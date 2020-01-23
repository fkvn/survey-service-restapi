package survey.model.surveychart;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import survey.model.core.User;

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
  private String name;

  @Column(name = "x_label")
  private String xLabel;

  @ElementCollection
  @CollectionTable(name = "survey_chart_xcoordinate", joinColumns = @JoinColumn(name = "chart_id"))
  @Column(name = "coordinate")
  @OrderColumn(name = "coordinate_order")
  private List<String> xCoordinates;

  @Column(name = "y_label")
  private String yLabel;

  @Column(name = "y_min")
  private Integer yMin;

  @Column(name = "y_max")
  private Integer yMax;

  @OneToMany(mappedBy = "chart")
  @OrderBy("name asc")
  private List<SurveyChartSeries> series;

  @ManyToOne
  @JoinColumn(name = "creator_id")
  private User creator;


  private Date date;

  private boolean deleted;

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

  public String getxLabel() {
    return xLabel;
  }

  public void setxLabel(String xLabel) {
    this.xLabel = xLabel;
  }

  public List<String> getxCoordinates() {
    return xCoordinates;
  }

  public void setxCoordinates(List<String> xCoordinates) {
    this.xCoordinates = xCoordinates;
  }

  public String getyLabel() {
    return yLabel;
  }

  public void setyLabel(String yLabel) {
    this.yLabel = yLabel;
  }

  public Integer getyMin() {
    return yMin;
  }

  public void setyMin(Integer yMin) {
    this.yMin = yMin;
  }

  public Integer getyMax() {
    return yMax;
  }

  public void setyMax(Integer yMax) {
    this.yMax = yMax;
  }

  public List<SurveyChartSeries> getSeries() {
    return series;
  }

  public void setSeries(List<SurveyChartSeries> series) {
    this.series = series;
  }

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

}
