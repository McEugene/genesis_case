package be.rha.genesiscase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author rhardenne
 * @since 26/05/2020
 */
@Entity
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Job {
  public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne(optional = false)
  private Contact contact;
  @ManyToOne(optional = false)
  private Company company;
  @JsonFormat(pattern = DATE_FORMAT_YYYY_MM_DD)
  @NotNull
  private LocalDate startDate;
  @JsonFormat(pattern = DATE_FORMAT_YYYY_MM_DD)
  private LocalDate endDate;

  public Long getId() {
    return id;
  }

  public Job setId(Long id) {
    this.id = id;
    return this;
  }

  public Contact getContact() {
    return contact;
  }

  public Job setContact(Contact contact) {
    this.contact = contact;
    return this;
  }

  public Company getCompany() {
    return company;
  }

  public Job setCompany(Company company) {
    this.company = company;
    return this;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public Job setStartDate(LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public Job setEndDate(LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }
}
