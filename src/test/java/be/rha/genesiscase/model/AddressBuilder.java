package be.rha.genesiscase.model;

/**
 * @author rhardenne
 * @since 27/05/2020
 */
public final class AddressBuilder {
  private Long id;
  private String streetName;
  private String streetNumber;
  private int zipCode;
  private String country;

  private AddressBuilder() {
  }

  public static AddressBuilder anAddress() {
    return new AddressBuilder();
  }

  public static AddressBuilder aValidAddress() {
    return anAddress()
        .withStreetName("a street name")
        .withStreetNumber("1A")
        .withZipCode(5030)
        .withCountry("Belgium");
  }

  public AddressBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public AddressBuilder withStreetName(String streetName) {
    this.streetName = streetName;
    return this;
  }

  public AddressBuilder withStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
    return this;
  }

  public AddressBuilder withZipCode(int zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public AddressBuilder withCountry(String country) {
    this.country = country;
    return this;
  }

  public AddressBuilder but() {
    return anAddress().withId(id).withStreetName(streetName).withStreetNumber(streetNumber).withZipCode(zipCode).withCountry(country);
  }

  public Address build() {
    Address address = new Address();
    address.setId(id);
    address.setStreetName(streetName);
    address.setStreetNumber(streetNumber);
    address.setZipCode(zipCode);
    address.setCountry(country);
    return address;
  }
}
