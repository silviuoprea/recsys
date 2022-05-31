package ro.raccoon.recsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.raccoon.recsys.domain.Complement;

/**
 * Spring Data SQL repository for the Complement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComplementRepository extends JpaRepository<Complement, Long> {}
