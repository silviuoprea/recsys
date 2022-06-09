package ro.raccoon.recsys.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Products.
 */
@Entity
@Table(name = "products")
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties(value = { "product" }, allowSetters = true)
    private Set<Price> prices = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties(value = { "product" }, allowSetters = true)
    private Set<Specifications> specifications = new HashSet<>();

    @OneToMany(mappedBy = "complementProduct")
    @JsonIgnoreProperties(value = { "complementProduct", "mainProduct" }, allowSetters = true)
    private Set<Complement> complements = new HashSet<>();

    @OneToMany(mappedBy = "mainProduct")
    @JsonIgnoreProperties(value = { "complementProduct", "mainProduct" }, allowSetters = true)
    private Set<Complement> mainProducts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "products" }, allowSetters = true)
    private Brand brand;

    public Long getId() {
        return this.id;
    }

    public Products id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return this.productName;
    }

    public Products productName(String productName) {
        this.setProductName(productName);
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return this.description;
    }

    public Products description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Price> getPrices() {
        return this.prices;
    }

    public void setPrices(Set<Price> prices) {
        if (this.prices != null) {
            this.prices.forEach(i -> i.setProduct(null));
        }
        if (prices != null) {
            prices.forEach(i -> i.setProduct(this));
        }
        this.prices = prices;
    }

    public Products prices(Set<Price> prices) {
        this.setPrices(prices);
        return this;
    }

    public Products addPrices(Price price) {
        this.prices.add(price);
        price.setProduct(this);
        return this;
    }

    public Products removePrices(Price price) {
        this.prices.remove(price);
        price.setProduct(null);
        return this;
    }

    public Set<Specifications> getSpecifications() {
        return this.specifications;
    }

    public void setSpecifications(Set<Specifications> specifications) {
        if (this.specifications != null) {
            this.specifications.forEach(i -> i.setProduct(null));
        }
        if (specifications != null) {
            specifications.forEach(i -> i.setProduct(this));
        }
        this.specifications = specifications;
    }

    public Products specifications(Set<Specifications> specifications) {
        this.setSpecifications(specifications);
        return this;
    }

    public Products addSpecifications(Specifications specifications) {
        this.specifications.add(specifications);
        specifications.setProduct(this);
        return this;
    }

    public Products removeSpecifications(Specifications specifications) {
        this.specifications.remove(specifications);
        specifications.setProduct(null);
        return this;
    }

    public Set<Complement> getComplements() {
        return this.complements;
    }

    public void setComplements(Set<Complement> complements) {
        if (this.complements != null) {
            this.complements.forEach(i -> i.setComplementProduct(null));
        }
        if (complements != null) {
            complements.forEach(i -> i.setComplementProduct(this));
        }
        this.complements = complements;
    }

    public Products complements(Set<Complement> complements) {
        this.setComplements(complements);
        return this;
    }

    public Products addComplements(Complement complement) {
        this.complements.add(complement);
        complement.setComplementProduct(this);
        return this;
    }

    public Products removeComplements(Complement complement) {
        this.complements.remove(complement);
        complement.setComplementProduct(null);
        return this;
    }

    public Set<Complement> getMainProducts() {
        return this.mainProducts;
    }

    public void setMainProducts(Set<Complement> complements) {
        if (this.mainProducts != null) {
            this.mainProducts.forEach(i -> i.setMainProduct(null));
        }
        if (complements != null) {
            complements.forEach(i -> i.setMainProduct(this));
        }
        this.mainProducts = complements;
    }

    public Products mainProducts(Set<Complement> complements) {
        this.setMainProducts(complements);
        return this;
    }

    public Products addMainProducts(Complement complement) {
        this.mainProducts.add(complement);
        complement.setMainProduct(this);
        return this;
    }

    public Products removeMainProducts(Complement complement) {
        this.mainProducts.remove(complement);
        complement.setMainProduct(null);
        return this;
    }

    public Brand getBrand() {
        return this.brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Products brand(Brand brand) {
        this.setBrand(brand);
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Products)) {
            return false;
        }
        return id != null && id.equals(((Products) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Products{" +
            "id=" + getId() +
            ", productName='" + getProductName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
