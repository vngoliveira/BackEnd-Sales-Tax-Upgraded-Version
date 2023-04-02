package org.sales.services;

import java.math.BigDecimal;

public interface TaxCalculationService {
    BigDecimal taxCalculation(BigDecimal price);
    BigDecimal roundingRule(BigDecimal tax);
}
