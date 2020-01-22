package survey.model.survey;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import survey.model.question.QuestionSection;
import survey.model.user.User;


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
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private SurveyType type;

  @Column(nullable = false)
  private String description;

  @Column(name = "publish_date")
  private Calendar publishDate;

  @Column(name = "close_date")
  private Calendar closeDate;

  @Column(name = "created_date", nullable = false)
  private Date createdDate;

  @Column(nullable = false)
  private boolean deleted;

  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false)
  private User author;

  @OneToMany
  @JoinColumn(name = "survey_id")
  @OrderColumn(name = "section_index")
  private List<QuestionSection> questionSections;

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

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
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

}
