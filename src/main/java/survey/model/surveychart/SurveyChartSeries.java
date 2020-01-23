package survey.model.surveychart;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@Table(name = "survey_chart_serie")
public class SurveyChartSeries implements Serializable {
  /**
   * Default serialVersionUID.
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "chart_id")
  private SurveyChart chart;

  private String name;

  @OneToMany
  @JoinColumn(name = "series_id")
  @OrderColumn(name = "point_index")
  private List<SurveyChartPoint> points;


  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public SurveyChart getChart() {
    return chart;
  }


  public void setChart(SurveyChart chart) {
    this.chart = chart;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public List<SurveyChartPoint> getPoints() {
    return points;
  }


  public void setPoints(List<SurveyChartPoint> points) {
    this.points = points;
  }
}
