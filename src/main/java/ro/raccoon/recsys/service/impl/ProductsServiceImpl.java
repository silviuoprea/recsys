package ro.raccoon.recsys.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.raccoon.recsys.domain.Products;
import ro.raccoon.recsys.repository.ProductsRepository;
import ro.raccoon.recsys.service.ProductsService;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Products}.
 */
@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

    private final Logger log = LoggerFactory.getLogger(ProductsServiceImpl.class);

    private final ProductsRepository productsRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Products save(Products products) {
        log.debug("Request to save Products : {}", products);
        return productsRepository.save(products);
    }

    @Override
    public Products update(Products products) {
        log.debug("Request to save Products : {}", products);
        return productsRepository.save(products);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Products> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productsRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Products> findOne(Long id) {
        log.debug("Request to get Products : {}", id);
        return productsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Products : {}", id);
        productsRepository.deleteById(id);
    }
}
