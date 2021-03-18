package br.com.proprietyservice.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Propriety {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long proprietyId;

  @NotEmpty(message = "The field userId is mandatory!")
  private String userId;

  private double area;

  private double builtArea;

  private double ruralArea;

  private String description;

  @ElementCollection
  @OneToMany(cascade = CascadeType.ALL)
  private List<Coordinate> coordinates;

  public Long getProprietyId() {
    return proprietyId;
  }

  public void setProprietyId(Long proprietyId) {
    this.proprietyId = proprietyId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public double getArea() {
    return area;
  }

  public void setArea(double area) {
    this.area = area;
  }

  public double getBuiltArea() {
    return builtArea;
  }

  public void setBuiltArea(double builtArea) {
    this.builtArea = builtArea;
  }

  public double getRuralArea() {
    return ruralArea;
  }

  public void setRuralArea(double ruralArea) {
    this.ruralArea = ruralArea;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public List<Coordinate> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }
}
