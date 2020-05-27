package be.rha.genesiscase.controller;

import be.rha.genesiscase.model.Address;
import be.rha.genesiscase.model.Company;
import be.rha.genesiscase.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author rhardenne
 * @since 26/05/2020
 */
@RestController
@RequestMapping("companies")
@Validated
public class CompanyController {

  private final CompanyRepository companyRepository;

  public CompanyController(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  @GetMapping(produces = "application/json")
  public List<Company> getAll() {
    // only expose this for small applications with few companies
    return companyRepository.findAll();
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  public Company findById(@PathVariable(value = "id") @Min(1) Long id) {
    return companyRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find company with id %s", id)));
  }

  @PostMapping(produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Company create(@Valid @RequestBody Company newCompany) {
    // TODO validate
    return companyRepository.save(newCompany);
  }

  @PutMapping(value = "/{id}", produces = "application/json")
  public Company update(@PathVariable(value = "id") @Min(1) Long id, @Valid @RequestBody Company companyToUpdate) {
    // TODO validate
    return companyRepository.findById(id)
        .map(oldCompany -> {
          oldCompany.merge(companyToUpdate);
          return companyRepository.save(oldCompany);
        })
        .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find company with id %s", id)));
  }

  @PostMapping(value = "/{id}/primary", produces = "application/json")
  public Company updatePrimaryAddress(@PathVariable(value = "id") @Min(1) Long id, @Valid @RequestBody Address newAddress) {
    // TODO validate
    return companyRepository.findById(id)
        .map(company -> {
          company.updatePrimaryAddress(newAddress);
          return companyRepository.save(company);
        })
        .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find company with id %s", id)));
  }

  @PostMapping(value = "/{id}/secondary", produces = "application/json")
  public Company addSecondaryAddress(@PathVariable(value = "id") @Min(1) Long id, @Valid @RequestBody Address newAddress) {
    // TODO validate
    return companyRepository.findById(id)
        .map(company -> {
          company.addSecondaryAddress(newAddress);
          return companyRepository.save(company);
        })
        .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find company with id %s", id)));
  }

}
