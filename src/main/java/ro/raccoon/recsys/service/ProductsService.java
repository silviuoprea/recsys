package ro.raccoon.recsys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ro.raccoon.recsys.domain.Products;

import java.util.Optional;

/**
 * Service Interface for managing {@link Products}.
 */
public interface ProductsService {
    /**
     * Save a products.
     *
     * @param products the entity to save.
     * @return the persisted entity.
     */
    Products save(Products products);

    /**
     * Updates a products.
     *
     * @param products the entity to update.
     * @return the persisted entity.
     */
    Products update(Products products);

    /**
     * Partially updates a products.
     *
     * @param products the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Products> partialUpdate(Products products);

    /**
     * Get all the products.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Products> findAll(Pageable pageable);

    /**
     * Get the "id" products.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Products> findOne(Long id);

    /**
     * Delete the "id" products.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
