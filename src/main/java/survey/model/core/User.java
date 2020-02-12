package survey.model.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import survey.util.Views;

/**
 * Project description.
 * 
 * @author kevinngo
 *
 */

@Entity
@Table(name = "user")
public class User implements Serializable {

  /**
   * Default serialVersionUID.
   */
  private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue
  @JsonView(Views.Public.class)
  private Long id;

  @Column(nullable = false, unique = true)
  @JsonView(Views.Public.class)
  private String username;

  @JsonIgnore
  @Column(nullable = false)
  private String password;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
