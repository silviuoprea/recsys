package ro.raccoon.recsys.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.raccoon.recsys.domain.Products;
import ro.raccoon.recsys.repository.ProductsRepository;
import ro.raccoon.recsys.service.ProductsService;
import ro.raccoon.recsys.utils.MyUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link Products}.
 */
@RestController
@RequestMapping("/api")
public class ProductsResource {

    private final Logger log = LoggerFactory.getLogger(ProductsResource.class);

    private static final String ENTITY_NAME = "products";

    @Value("${RecSys.clientApp.name}")
    private String applicationName;

    private final ProductsService productsService;

    private final ProductsRepository productsRepository;

    public ProductsResource(ProductsService productsService, ProductsRepository productsRepository) {
        this.productsService = productsService;
        this.productsRepository = productsRepository;
    }

    /**
     * {@code POST  /products} : Create a new products.
     *
     * @param products the products to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new products, or with status {@code 400 (Bad Request)} if the products has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/products")
    public ResponseEntity<Products> createProducts(@RequestBody Products products) throws URISyntaxException {
        log.debug("REST request to save Products : {}", products);
        if (products.getId() != null) {
            return ResponseEntity
                    .badRequest()
                    .headers(MyUtils.createFailureAlert(applicationName, false, ENTITY_NAME, products.getId().toString(),"A new products cannot already have an ID" ))
                    .body(null);
        }
        Products result = productsService.save(products);
        return ResponseEntity
            .created(new URI("/api/products/" + result.getId()))
            .headers(MyUtils.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /products/:id} : Updates an existing products.
     *
     * @param id the id of the products to save.
     * @param products the products to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated products,
     * or with status {@code 400 (Bad Request)} if the products is not valid,
     * or with status {@code 500 (Internal Server Error)} if the products couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<Products> updateProducts(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Products products
    ) throws URISyntaxException {
        log.debug("REST request to update Products : {}, {}", id, products);
        if (products.getId() == null) {
            return ResponseEntity
                    .badRequest()
                    .headers(MyUtils.createFailureAlert(applicationName, false, ENTITY_NAME, products.getId().toString(),"Invalid ID" ))
                    .body(null);
        }
        if (!Objects.equals(id, products.getId())) {
            return ResponseEntity
                    .badRequest()
                    .headers(MyUtils.createFailureAlert(applicationName, false, ENTITY_NAME, products.getId().toString(),"Invalid ID" ))
                    .body(null);
        }

        if (!productsRepository.existsById(id)) {
            return ResponseEntity
                    .badRequest()
                    .headers(MyUtils.createFailureAlert(applicationName, false, ENTITY_NAME, products.getId().toString(),"ID not found" ))
                    .body(null);
        }

        Products result = productsService.update(products);
        return ResponseEntity
            .ok()
            .headers(MyUtils.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, products.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /products/:id} : Partial updates given fields of an existing products, field will ignore if it is null
     *
     * @param id the id of the products to save.
     * @param products the products to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated products,
     * or with status {@code 400 (Bad Request)} if the products is not valid,
     * or with status {@code 404 (Not Found)} if the products is not found,
     * or with status {@code 500 (Internal Server Error)} if the products couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/products/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Products> partialUpdateProducts(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Products products
    ) throws URISyntaxException {
        log.debug("REST request to partial update Products partially : {}, {}", id, products);
        if (products.getId() == null) {
            return ResponseEntity
                    .badRequest()
                    .headers(MyUtils.createFailureAlert(applicationName, false, ENTITY_NAME, products.getId().toString(),"Invalid ID" ))
                    .body(null);
        }
        if (!Objects.equals(id, products.getId())) {
            return ResponseEntity
                    .badRequest()
                    .headers(MyUtils.createFailureAlert(applicationName, false, ENTITY_NAME, products.getId().toString(),"Invalid ID" ))
                    .body(null);
        }

        if (!productsRepository.existsById(id)) {
            return ResponseEntity
                    .badRequest()
                    .headers(MyUtils.createFailureAlert(applicationName, false, ENTITY_NAME, products.getId().toString(),"ID not found" ))
                    .body(null);
        }

        Optional<Products> result = productsService.partialUpdate(products);

        return MyUtils.wrapOrNotFound(
            result,
            MyUtils.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, products.getId().toString())
        );
    }

    /**
     * {@code GET  /products} : get all the products.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of products in body.
     */
    @GetMapping("/products")
    public ResponseEntity<List<Products>> getAllProducts(Pageable pageable) {
        log.debug("REST request to get a page of Products");
        Page<Products> page = productsService.findAll(pageable);
        HttpHeaders headers = MyUtils.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /products/:id} : get the "id" products.
     *
     * @param id the id of the products to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the products, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Products> getProducts(@PathVariable Long id) {
        log.debug("REST request to get Products : {}", id);
        Optional<Products> products = productsService.findOne(id);
        return MyUtils.wrapOrNotFound(products);
    }

    /**
     * {@code DELETE  /products/:id} : delete the "id" products.
     *
     * @param id the id of the products to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProducts(@PathVariable Long id) {
        log.debug("REST request to delete Products : {}", id);
        productsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(MyUtils.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
