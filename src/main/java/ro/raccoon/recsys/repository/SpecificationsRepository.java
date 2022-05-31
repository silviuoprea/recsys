package ro.raccoon.recsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.raccoon.recsys.domain.Specifications;

/**
 * Spring Data SQL repository for the Specifications entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpecificationsRepository extends JpaRepository<Specifications, Long> {}
