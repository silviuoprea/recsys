package ro.raccoon.recsys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ro.raccoon.recsys.domain.Specifications;

import java.util.Optional;

/**
 * Service Interface for managing {@link Specifications}.
 */
public interface SpecificationsService {
    /**
     * Save a specifications.
     *
     * @param specifications the entity to save.
     * @return the persisted entity.
     */
    Specifications save(Specifications specifications);

    /**
     * Updates a specifications.
     *
     * @param specifications the entity to update.
     * @return the persisted entity.
     */
    Specifications update(Specifications specifications);

    /**
     * Partially updates a specifications.
     *
     * @param specifications the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Specifications> partialUpdate(Specifications specifications);

    /**
     * Get all the specifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Specifications> findAll(Pageable pageable);

    /**
     * Get the "id" specifications.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Specifications> findOne(Long id);

    /**
     * Delete the "id" specifications.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
