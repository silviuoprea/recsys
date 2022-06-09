package ro.raccoon.recsys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ro.raccoon.recsys.domain.Complement;

import java.util.Optional;

/**
 * Service Interface for managing {@link Complement}.
 */
public interface ComplementService {
    /**
     * Save a complement.
     *
     * @param complement the entity to save.
     * @return the persisted entity.
     */
    Complement save(Complement complement);

    /**
     * Updates a complement.
     *
     * @param complement the entity to update.
     * @return the persisted entity.
     */
    Complement update(Complement complement);

    /**
     * Get all the complements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Complement> findAll(Pageable pageable);

    /**
     * Get the "id" complement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Complement> findOne(Long id);

    /**
     * Delete the "id" complement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
