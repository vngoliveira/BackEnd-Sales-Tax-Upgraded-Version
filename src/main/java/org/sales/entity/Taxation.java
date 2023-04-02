package org.sales.entity;

import java.math.BigDecimal;

public class Taxation {

    private BigDecimal taxedPrice;
    private BigDecimal tax;

    public Taxation(BigDecimal taxedPrice, BigDecimal tax) {
        this.taxedPrice = taxedPrice;
        this.tax = tax;
    }

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }

    public BigDecimal getTax() {
        return tax;
    }

}
