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
  private double lat;

  @NotNull(message = "The field longitude is mandatory!")
  private double lng;

  public void setCoordinateId(Long coordinateId) {
    this.coordinateId = coordinateId;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLng() {
    return lng;
  }

  public void setLng(double lng) {
    this.lng = lng;
  }
}
