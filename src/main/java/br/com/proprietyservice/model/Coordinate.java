package br.com.proprietyservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Coordinate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long coordinateId;

  @NotEmpty(message = "The field latitude is mandatory!")
  private double latitude;

  @NotEmpty(message = "The field longitude is mandatory!")
  private double longitude;

  public Long getCoordinateId() {
    return coordinateId;
  }

  public void setCoordinateId(Long coordinateId) {
    this.coordinateId = coordinateId;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
}
