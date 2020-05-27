package be.rha.genesiscase.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author rhardenne
 * @since 27/05/2020
 */
class ContactTest {

  @Test
  void merge() {
    Contact newContact = ContactBuilder.aContact()
        .withFirstName("Joe")
        .withLastName("Eoj")
        .withFreelance(true)
        .withTvaNumber("BE123")
        .withAddress(AddressBuilder.aValidAddress().build())
        .build();
    Contact contact = ContactBuilder.aValidContact().build();
    contact.merge(newContact);
    assertEquals(newContact.getFirstName(), contact.getFirstName());
    assertEquals(newContact.getLastName(), contact.getLastName());
    assertEquals(newContact.isFreelance(), contact.isFreelance());
    assertEquals(newContact.getTvaNumber(), contact.getTvaNumber());
  }

  @Test
  void updateAddress() {
    // TODO
  }

  @Test
  void addJob() {
    // TODO
    // also check a job has a company
  }

  @Test
  void validateTvaNumberNoFreelance() {
    ContactBuilder.aValidContact()
        .build()
        .validateTvaNumber();
    // no exception
  }

  @Test
  void validateTvaNumberFreelance() {
    ContactBuilder.aValidContact()
        .withFreelance(true)
        .withTvaNumber("BE123")
        .build()
        .validateTvaNumber();
    // no exception
  }

  @Test
  void validateTvaNumberFreelanceWithoutTvaNumber() {
    Contact contact = ContactBuilder.aValidContact()
        .withFreelance(true)
        .build();
    assertThrows(RuntimeException.class, contact::validateTvaNumber);
  }
}