package ro.raccoon.recsys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.raccoon.recsys.domain.Products;
import ro.raccoon.recsys.repository.ProductsRepository;
import ro.raccoon.recsys.service.ProductsService;
import ro.raccoon.recsys.utils.MyUtils;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@EnableSwagger2
@SpringBootApplication
public class RecsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecsysApplication.class, args);
	}

	/**
	 * REST controller for managing {@link Products}.
	 */
	@RestController
	@RequestMapping("/api")
	public static class ProductsResource {

		private final Logger log = LoggerFactory.getLogger(ProductsResource.class);

		private final ProductsService productsService;

		public ProductsResource(ProductsService productsService, ProductsRepository productsRepository) {
			this.productsService = productsService;
		}

		/**
		 * {@code POST  /products} : Create a new products.
		 *
		 * @param products the products to create.
		 * @return the {@link ResponseEntity}.
		 * @throws URISyntaxException if the Location URI syntax is incorrect.
		 */
		@PostMapping("/products")
		public ResponseEntity<Products> createProducts(@RequestBody Products products) throws URISyntaxException {
			log.debug("REST request to save Products : {}", products);
			Products result = productsService.save(products);
			return ResponseEntity
				.created(new URI("/api/products/" + result.getId()))
				.body(result);
		}

		/**
		 * {@code PUT  /products/:id} : Updates an existing products.
		 *
		 * @param id the id of the products to save.
		 * @param products the products to update.
		 * @return the {@link ResponseEntity}
		 */
		@PutMapping("/products/{id}")
		public ResponseEntity<Products> updateProducts(
			@PathVariable(value = "id", required = false) final Long id,
			@RequestBody Products products
		) {
			log.debug("REST request to update Products : {}, {}", id, products);

			Products result = productsService.update(products);
			return ResponseEntity
				.ok()
				.body(result);
		}

		/**
		 * {@code GET  /products} : get all the products.
		 *
		 * @param pageable the pagination information.
		 * @return the {@link ResponseEntity} with the list of products in body.
		 */
		@GetMapping("/products")
		public ResponseEntity<List<Products>> getAllProducts(Pageable pageable) {
			log.debug("REST request to get a page of Products");
			Page<Products> page = productsService.findAll(pageable);
			return ResponseEntity.ok().body(page.getContent());
		}

		/**
		 * {@code GET  /products/:id} : get the "id" products.
		 *
		 * @param id the id of the products to retrieve.
		 * @return the {@link ResponseEntity} with body the products.
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
		 * @return the {@link ResponseEntity}.
		 */
		@DeleteMapping("/products/{id}")
		public ResponseEntity<Void> deleteProducts(@PathVariable Long id) {
			log.debug("REST request to delete Products : {}", id);
			productsService.delete(id);
			return ResponseEntity
				.noContent()
				.build();
		}
	}
}
