package be.rha.genesiscase.repository;

import be.rha.genesiscase.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rhardenne
 * @since 25/05/2020
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
