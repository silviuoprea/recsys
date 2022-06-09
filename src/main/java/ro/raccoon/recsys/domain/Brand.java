package ro.raccoon.recsys.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A Brand.
 */
@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "brand_name")
    private String brandName;

    @OneToMany(mappedBy = "brand")
    @JsonIgnoreProperties(value = { "prices", "specifications", "complements", "mainProducts", "brand" }, allowSetters = true)
    private Set<Products> products = new HashSet<>();


    public Long getId() {
        return this.id;
    }

    public Brand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public Brand brandName(String brandName) {
        this.setBrandName(brandName);
        return this;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Set<Products> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Products> products) {
        if (this.products != null) {
            this.products.forEach(i -> i.setBrand(null));
        }
        if (products != null) {
            products.forEach(i -> i.setBrand(this));
        }
        this.products = products;
    }

    public Brand products(Set<Products> products) {
        this.setProducts(products);
        return this;
    }

    public Brand addProducts(Products products) {
        this.products.add(products);
        products.setBrand(this);
        return this;
    }

    public Brand removeProducts(Products products) {
        this.products.remove(products);
        products.setBrand(null);
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Brand)) {
            return false;
        }
        return id != null && id.equals(((Brand) o).id);
    }

    @Override
    public String toString() {
        return "Brand{" +
            "id=" + getId() +
            ", brandName='" + getBrandName() + "'" +
            "}";
    }
}
