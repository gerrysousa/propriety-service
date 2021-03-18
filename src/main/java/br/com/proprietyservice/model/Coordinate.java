package br.com.proprietyservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Coordinate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "coordinateId")
  private Long coordinateId;

  @NotNull(message = "The field latitude is mandatory!")
  private double latitude;

  @NotNull(message = "The field longitude is mandatory!")
  private double longitude;

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
