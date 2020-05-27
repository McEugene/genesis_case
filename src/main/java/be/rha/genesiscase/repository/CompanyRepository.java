package be.rha.genesiscase.repository;

import be.rha.genesiscase.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rhardenne
 * @since 26/05/2020
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
