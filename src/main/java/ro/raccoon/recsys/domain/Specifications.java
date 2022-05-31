package ro.raccoon.recsys.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Specifications.
 */
@Entity
@Table(name = "specifications")
public class Specifications implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "product_size")
    private Integer productSize;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "color")
    private String color;

    @Column(name = "material")
    private String material;

    @Column(name = "specification_name")
    private String specificationName;

    @ManyToOne
    @JsonIgnoreProperties(value = { "prices", "specifications", "complements", "mainProducts", "brand" }, allowSetters = true)
    private Products product;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Specifications id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductSize() {
        return this.productSize;
    }

    public Specifications productSize(Integer productSize) {
        this.setProductSize(productSize);
        return this;
    }

    public void setProductSize(Integer productSize) {
        this.productSize = productSize;
    }

    public Double getWeight() {
        return this.weight;
    }

    public Specifications weight(Double weight) {
        this.setWeight(weight);
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return this.color;
    }

    public Specifications color(String color) {
        this.setColor(color);
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return this.material;
    }

    public Specifications material(String material) {
        this.setMaterial(material);
        return this;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSpecificationName() {
        return this.specificationName;
    }

    public Specifications specificationName(String specificationName) {
        this.setSpecificationName(specificationName);
        return this;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public Products getProduct() {
        return this.product;
    }

    public void setProduct(Products products) {
        this.product = products;
    }

    public Specifications product(Products products) {
        this.setProduct(products);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Specifications)) {
            return false;
        }
        return id != null && id.equals(((Specifications) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Specifications{" +
            "id=" + getId() +
            ", productSize=" + getProductSize() +
            ", weight=" + getWeight() +
            ", color='" + getColor() + "'" +
            ", material='" + getMaterial() + "'" +
            ", specificationName='" + getSpecificationName() + "'" +
            "}";
    }
}
