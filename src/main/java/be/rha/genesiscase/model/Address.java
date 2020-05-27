package be.rha.genesiscase.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author rhardenne
 * @since 25/05/2020
 */
@Entity
public class Address {
  @Id
  @GeneratedValue
  private Long id;
  @NotEmpty
  private String streetName;
  @NotEmpty
  private String streetNumber;
  @Min(1000)
  @Max(9999)
  private int zipCode;
  @NotEmpty
  private String country;

  public Long getId() {
    return id;
  }

  public Address setId(Long id) {
    this.id = id;
    return this;
  }

  public String getStreetName() {
    return streetName;
  }

  public Address setStreetName(String streetName) {
    this.streetName = streetName;
    return this;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public Address setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
    return this;
  }

  public int getZipCode() {
    return zipCode;
  }

  public Address setZipCode(int zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public String getCountry() {
    return country;
  }

  public Address setCountry(String country) {
    this.country = country;
    return this;
  }

  public void merge(Address toMerge) {
    this.streetName = toMerge.streetName;
    this.streetNumber = toMerge.streetNumber;
    this.zipCode = toMerge.zipCode;
    this.country = toMerge.country;
  }
}
