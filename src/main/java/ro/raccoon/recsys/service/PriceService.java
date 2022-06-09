package ro.raccoon.recsys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ro.raccoon.recsys.domain.Price;

import java.util.Optional;

/**
 * Service Interface for managing {@link Price}.
 */
public interface PriceService {
    /**
     * Save a price.
     *
     * @param price the entity to save.
     * @return the persisted entity.
     */
    Price save(Price price);

    /**
     * Updates a price.
     *
     * @param price the entity to update.
     * @return the persisted entity.
     */
    Price update(Price price);

    /**
     * Get all the prices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Price> findAll(Pageable pageable);

    /**
     * Get the "id" price.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Price> findOne(Long id);

    /**
     * Delete the "id" price.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
