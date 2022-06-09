package ro.raccoon.recsys.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Price.
 */
@Entity
@Table(name = "price")
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "base_price")
    private Integer basePrice;

    @Column(name = "cost_price")
    private Integer costPrice;

    @Column(name = "price_after_tax")
    private Integer priceAfterTax;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "prices", "specifications", "complements", "mainProducts", "brand" }, allowSetters = true)
    private Products product;


    public Long getId() {
        return this.id;
    }

    public Price id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBasePrice() {
        return this.basePrice;
    }

    public Price basePrice(Integer basePrice) {
        this.setBasePrice(basePrice);
        return this;
    }

    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getCostPrice() {
        return this.costPrice;
    }

    public Price costPrice(Integer costPrice) {
        this.setCostPrice(costPrice);
        return this;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getPriceAfterTax() {
        return this.priceAfterTax;
    }

    public Price priceAfterTax(Integer priceAfterTax) {
        this.setPriceAfterTax(priceAfterTax);
        return this;
    }

    public void setPriceAfterTax(Integer priceAfterTax) {
        this.priceAfterTax = priceAfterTax;
    }

    public Instant getStartDate() {
        return this.startDate;
    }

    public Price startDate(Instant startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return this.endDate;
    }

    public Price endDate(Instant endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Products getProduct() {
        return this.product;
    }

    public void setProduct(Products products) {
        this.product = products;
    }

    public Price product(Products products) {
        this.setProduct(products);
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Price)) {
            return false;
        }
        return id != null && id.equals(((Price) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Price{" +
            "id=" + getId() +
            ", basePrice=" + getBasePrice() +
            ", costPrice=" + getCostPrice() +
            ", priceAfterTax=" + getPriceAfterTax() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
