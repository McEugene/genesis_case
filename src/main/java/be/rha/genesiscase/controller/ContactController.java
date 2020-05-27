package be.rha.genesiscase.controller;

import be.rha.genesiscase.model.Address;
import be.rha.genesiscase.model.Company;
import be.rha.genesiscase.model.Contact;
import be.rha.genesiscase.model.Job;
import be.rha.genesiscase.repository.CompanyRepository;
import be.rha.genesiscase.repository.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

/**
 * @author rhardenne
 * @since 25/05/2020
 */
@RestController
@RequestMapping("contacts")
@Validated
public class ContactController {

  private final ContactRepository contactRepository;
  private final CompanyRepository companyRepository;

  public ContactController(ContactRepository contactRepository, CompanyRepository companyRepository) {
    this.contactRepository = contactRepository;
    this.companyRepository = companyRepository;
  }

  @GetMapping(produces = "application/json")
  public List<Contact> getAll() {
    // only expose this for small applications with few contacts
    return contactRepository.findAll();
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  public Contact findById(@PathVariable(value = "id") @Min(1) Long id) {
    return contactRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find contact with id %s", id)));
  }

  @PostMapping(produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Contact create(@Valid @RequestBody Contact newContact) {
    return contactRepository.save(newContact);
  }

  @PutMapping(value = "/{id}", produces = "application/json")
  public Contact update(@PathVariable(value = "id") @Min(1) Long id, @Valid @RequestBody Contact contactToUpdate) {
    return contactRepository.findById(id)
        .map(oldContact -> {
          oldContact.merge(contactToUpdate);
          return contactRepository.save(oldContact);
        })
        .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find contact with id %s", id)));
  }

  @PostMapping(value = "/{id}/address", produces = "application/json")
  public Contact updatePrimaryAddress(@PathVariable(value = "id") @Min(1) Long id, @Valid @RequestBody Address newAddress) {
    return contactRepository.findById(id)
        .map(contact -> {
          contact.updateAddress(newAddress);
          return contactRepository.save(contact);
        })
        .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find contact with id %s", id)));
  }

  @PostMapping(value = "/{id}/jobForCompany/{companyId}", produces = "application/json")
  public Contact addJob(@PathVariable(value = "id") @Min(1) Long id, @PathVariable(value = "companyId") @Min(1) Long companyId, @Valid @RequestBody Job job) {
    Company company = tryFindCompany(companyId)
        .orElseThrow(() -> new RuntimeException(String.format("Could not add job for contact %s because of unknown company %s", id, companyId)));
    return contactRepository.findById(id)
        .map(contact -> {
          job.setCompany(company);
          contact.addJob(job);
          return contactRepository.save(contact);
        })
        .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find contact with id %s", id)));
  }

  private Optional<Company> tryFindCompany(Long companyId) {
    return companyRepository.findById(companyId);
  }

}
