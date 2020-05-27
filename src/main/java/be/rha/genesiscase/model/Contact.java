package be.rha.genesiscase.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rhardenne
 * @since 25/05/2020
 */
@Entity
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Contact {
  @Id
  @GeneratedValue
  private Long id;
  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  private boolean freelance;
  private String tvaNumber;
  @OneToOne(cascade = CascadeType.ALL, optional = false)
  private Address address;
  @OneToMany(cascade = CascadeType.ALL)
  private List<Job> jobs;

  public Long getId() {
    return id;
  }

  public Contact setId(Long id) {
    this.id = id;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public Contact setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public Contact setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public boolean isFreelance() {
    return freelance;
  }

  public Contact setFreelance(boolean freelance) {
    this.freelance = freelance;
    return this;
  }

  public String getTvaNumber() {
    return tvaNumber;
  }

  public Contact setTvaNumber(String tvaNumber) {
    this.tvaNumber = tvaNumber;
    return this;
  }

  public Address getAddress() {
    return address;
  }

  public Contact setAddress(Address address) {
    this.address = address;
    return this;
  }

  public List<Job> getJobs() {
    return jobs;
  }

  public Contact setJobs(List<Job> jobs) {
    this.jobs = jobs;
    return this;
  }

  public void merge(Contact toMerge) {
    this.firstName = toMerge.firstName;
    this.lastName = toMerge.lastName;
    this.freelance = toMerge.freelance;
    this.tvaNumber = toMerge.tvaNumber;
    validateTvaNumber();
  }

  public void updateAddress(Address address) {
    this.address.merge(address);
  }

  public void addJob(Job job) {
    job.setContact(this);
    if (jobs == null) {
      jobs = new ArrayList<>();
    }
    jobs.add(job);
  }

  public void validateTvaNumber() {
    if (freelance && tvaNumber == null) {
      //TODO we could use some specific exception here
      throw new RuntimeException("Freelancer should have TVA number");
    }
  }
}
