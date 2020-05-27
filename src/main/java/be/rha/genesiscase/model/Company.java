package be.rha.genesiscase.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rhardenne
 * @since 25/05/2020
 */
@Entity
public class Company {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String tvaNumber;
  @OneToOne(cascade = CascadeType.ALL, optional = false)
  private Address primaryAddress;
  @OneToMany(cascade = CascadeType.ALL)
  private List<Address> secondaryAddresses;

  public Long getId() {
    return id;
  }

  public Company setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Company setName(String name) {
    this.name = name;
    return this;
  }

  public String getTvaNumber() {
    return tvaNumber;
  }

  public Company setTvaNumber(String tvaNumber) {
    this.tvaNumber = tvaNumber;
    return this;
  }

  public Address getPrimaryAddress() {
    return primaryAddress;
  }

  public Company setPrimaryAddress(Address primaryAddress) {
    this.primaryAddress = primaryAddress;
    return this;
  }

  public List<Address> getSecondaryAddresses() {
    return secondaryAddresses;
  }

  public Company setSecondaryAddresses(List<Address> secondaryAddresses) {
    this.secondaryAddresses = secondaryAddresses;
    return this;
  }

  public void merge(Company toMerge) {
    this.name = toMerge.name;
    this.tvaNumber = toMerge.tvaNumber;
  }

  public void updatePrimaryAddress(Address address) {
    this.primaryAddress.merge(address);
  }

  public void addSecondaryAddress(Address address) {
    if (secondaryAddresses == null) {
      secondaryAddresses = new ArrayList<>();
    }
    secondaryAddresses.add(address);
  }
}
