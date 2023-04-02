package org.sales.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ImportedTaxCalculationService implements TaxCalculationService{
    BigDecimal tax;
    @Override
    public BigDecimal taxCalculation(BigDecimal price) {
        tax = price.multiply(BigDecimal.valueOf(0.05));
        tax = roundingRule(tax);
        return tax;
    }

    @Override
    public BigDecimal roundingRule(BigDecimal price) {
        BigDecimal roundedTax = tax.multiply(BigDecimal.valueOf(20)).setScale(0, RoundingMode.CEILING);
        return roundedTax.multiply(BigDecimal.valueOf(0.05)).setScale(2, RoundingMode.CEILING);
    }

}
