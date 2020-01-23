package survey.model.surveychart;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import survey.model.survey.Survey;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@Table(name = "survey_chart_point")
public class SurveyChartPoint implements Serializable {
  /**
   * Default serialVersionUID.
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "survey_id")
  private Survey survey;

  @Column(name = "section_index", nullable = false)
  private int sectionIndex;

  @Column(name = "question_index", nullable = false)
  private int questionIndex;

  @Column(name = "values_set", nullable = false)
  private boolean valuesSet;

  private Double min;
  private Double max;
  private Double average;
  private Double median;

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

  public int getSectionIndex() {
    return sectionIndex;
  }

  public void setSectionIndex(int sectionIndex) {
    this.sectionIndex = sectionIndex;
  }

  public int getQuestionIndex() {
    return questionIndex;
  }

  public void setQuestionIndex(int questionIndex) {
    this.questionIndex = questionIndex;
  }

  public boolean isValuesSet() {
    return valuesSet;
  }

  public void setValuesSet(boolean valuesSet) {
    this.valuesSet = valuesSet;
  }

  public Double getMin() {
    return min;
  }

  public void setMin(Double min) {
    this.min = min;
  }

  public Double getMax() {
    return max;
  }

  public void setMax(Double max) {
    this.max = max;
  }

  public Double getAverage() {
    return average;
  }

  public void setAverage(Double average) {
    this.average = average;
  }

  public Double getMedian() {
    return median;
  }

  public void setMedian(Double median) {
    this.median = median;
  }
}
