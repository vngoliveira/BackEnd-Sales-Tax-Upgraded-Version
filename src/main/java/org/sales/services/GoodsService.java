package org.sales.services;

import org.sales.entity.Goods;
import org.sales.entity.Taxation;
import org.sales.exceptions.GoodsNameIsEmptyOrNullExceptions;
import org.sales.exceptions.GoodsPriceLessThanZeroExceptions;

import java.math.BigDecimal;

public class GoodsService {

    private TaxCalculationService taxCalculationService;

    public GoodsService(TaxCalculationService taxCalculationService) {
        this.taxCalculationService = taxCalculationService;
    }

    public void calculation(Goods goods) throws GoodsPriceLessThanZeroExceptions {
        BigDecimal price = goods.getPrice();
        BigDecimal tax = taxCalculationService.taxCalculation(price);
        BigDecimal taxedPrice = price.add(tax);

        goods.getTaxation().add(new Taxation(taxedPrice, tax));
    }
}
