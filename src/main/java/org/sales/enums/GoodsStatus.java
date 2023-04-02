package org.sales.enums;

import org.sales.exceptions.GoodsInvalidOptionExceptions;

public enum GoodsStatus {
    IMPORTED,
    NATIONAL;

    public static GoodsStatus fromValue(int goodsStatus) throws GoodsInvalidOptionExceptions {
        for (GoodsStatus type : values()) {
            if (type.ordinal() == goodsStatus) {
                return type;
            }
        }
        throw new GoodsInvalidOptionExceptions("Invalid input! The " + goodsStatus + " option doesn't exist for the product origin.");
    }

    public static void showElementsOfStatusEnum() {
        for (GoodsStatus types : values()) {
            System.out.println(types.ordinal() + " - " + types);
        }
    }
}