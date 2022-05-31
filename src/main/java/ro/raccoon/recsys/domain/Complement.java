package ro.raccoon.recsys.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Complement.
 */
@Entity
@Table(name = "complement")
public class Complement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "is_accessory_to")
    private Boolean isAccessoryTo;

    @ManyToOne
    @JsonIgnoreProperties(value = { "prices", "specifications", "complements", "mainProducts", "brand" }, allowSetters = true)
    private Products complementProduct;

    @ManyToOne
    @JsonIgnoreProperties(value = { "prices", "specifications", "complements", "mainProducts", "brand" }, allowSetters = true)
    private Products mainProduct;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Complement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsAccessoryTo() {
        return this.isAccessoryTo;
    }

    public Complement isAccessoryTo(Boolean isAccessoryTo) {
        this.setIsAccessoryTo(isAccessoryTo);
        return this;
    }

    public void setIsAccessoryTo(Boolean isAccessoryTo) {
        this.isAccessoryTo = isAccessoryTo;
    }

    public Products getComplementProduct() {
        return this.complementProduct;
    }

    public void setComplementProduct(Products products) {
        this.complementProduct = products;
    }

    public Complement complementProduct(Products products) {
        this.setComplementProduct(products);
        return this;
    }

    public Products getMainProduct() {
        return this.mainProduct;
    }

    public void setMainProduct(Products products) {
        this.mainProduct = products;
    }

    public Complement mainProduct(Products products) {
        this.setMainProduct(products);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Complement)) {
            return false;
        }
        return id != null && id.equals(((Complement) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Complement{" +
            "id=" + getId() +
            ", isAccessoryTo='" + getIsAccessoryTo() + "'" +
            "}";
    }
}
