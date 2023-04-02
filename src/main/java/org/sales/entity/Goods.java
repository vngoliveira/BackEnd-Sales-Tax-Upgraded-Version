package org.sales.entity;

import org.sales.enums.GoodsStatus;
import org.sales.enums.GoodsTypes;
import org.sales.exceptions.GoodsNameIsEmptyOrNullExceptions;
import org.sales.exceptions.GoodsPriceLessThanZeroExceptions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Goods {

    private BigDecimal price;
    private String name;
    private GoodsStatus goodsStatus;
    private GoodsTypes goodsTypes;
    List<Taxation> taxation = new ArrayList<>();

    public Goods(BigDecimal price, String name, GoodsStatus goodsStatus, GoodsTypes goodsTypes) {
        this.price = price;
        this.name = name;
        this.goodsStatus = goodsStatus;
        this.goodsTypes = goodsTypes;
    }

    public BigDecimal getPrice() throws GoodsPriceLessThanZeroExceptions {
        if(price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new GoodsPriceLessThanZeroExceptions("Price should be than zero.");
        }
        return price;
    }

    public String getName() throws GoodsNameIsEmptyOrNullExceptions{
        if(name.isBlank() || name.isEmpty()) {
            throw new GoodsNameIsEmptyOrNullExceptions("A name is necessary for the product.");
        }
        return name;
    }

    public GoodsStatus getGoodsStatus() {
        return goodsStatus;
    }

    public GoodsTypes getGoodsTypes() {
        return goodsTypes;
    }

    public List<Taxation> getTaxation() {
        return taxation;
    }

}