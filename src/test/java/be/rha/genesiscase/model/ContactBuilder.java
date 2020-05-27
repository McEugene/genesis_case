package be.rha.genesiscase.model;

import java.util.List;

/**
 * @author rhardenne
 * @since 27/05/2020
 */
public final class ContactBuilder {
  private Long id;
  private String firstName;
  private String lastName;
  private boolean freelance;
  private String tvaNumber;
  private Address address;
  private List<Job> jobs;

  private ContactBuilder() {
  }

  public static ContactBuilder aContact() {
    return new ContactBuilder();
  }

  public static ContactBuilder aValidContact() {
    return aContact()
        .withFirstName("firstName")
        .withLastName("lastName")
        .withAddress(AddressBuilder.aValidAddress().build());
  }

  public ContactBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public ContactBuilder withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactBuilder withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactBuilder withFreelance(boolean freelance) {
    this.freelance = freelance;
    return this;
  }

  public ContactBuilder withTvaNumber(String tvaNumber) {
    this.tvaNumber = tvaNumber;
    return this;
  }

  public ContactBuilder withAddress(Address address) {
    this.address = address;
    return this;
  }

  public ContactBuilder withJobs(List<Job> jobs) {
    this.jobs = jobs;
    return this;
  }

  public ContactBuilder but() {
    return aContact().withId(id).withFirstName(firstName).withLastName(lastName).withFreelance(freelance).withTvaNumber(tvaNumber).withAddress(address).withJobs(jobs);
  }

  public Contact build() {
    Contact contact = new Contact();
    contact.setId(id);
    contact.setFirstName(firstName);
    contact.setLastName(lastName);
    contact.setFreelance(freelance);
    contact.setTvaNumber(tvaNumber);
    contact.setAddress(address);
    contact.setJobs(jobs);
    return contact;
  }
}
