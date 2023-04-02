package org.sales;
import org.sales.entity.Goods;
import org.sales.entity.Taxation;
import org.sales.enums.GoodsStatus;
import org.sales.enums.GoodsTypes;
import org.sales.exceptions.GoodsInvalidOptionExceptions;
import org.sales.exceptions.GoodsNameIsEmptyOrNullExceptions;
import org.sales.exceptions.GoodsPriceLessThanZeroExceptions;
import org.sales.services.GoodsService;
import org.sales.services.ImportedAndSalesTaxCalculationService;
import org.sales.services.ImportedTaxCalculationService;
import org.sales.services.SalesTaxCalculationService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        Goods goods;
        BigDecimal taxSum = new BigDecimal(0);
        BigDecimal priceSum = new BigDecimal(0);
        boolean exit;
        ArrayList<Goods> list = new ArrayList<>();

        try{
        do {
            System.out.println("Only foods, books, or drugs are untaxed products. The others are taxed.");
            System.out.println();
            GoodsTypes.showElementsOfTypesEnum();
            System.out.print("Set the product type according to the specification above: ");
            GoodsTypes goodsTypes = GoodsTypes.fromValue(scanner.nextInt());
            GoodsStatus.showElementsOfStatusEnum();
            System.out.print("Set the product origin according to the options above: ");
            GoodsStatus goodsStatus = GoodsStatus.fromValue(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Set a name to product: ");
            String name = scanner.nextLine();
            System.out.print("Set the product price: ");
            BigDecimal price = scanner.nextBigDecimal();

            goods = new Goods(price, name, goodsStatus, goodsTypes);
            goods.getName();
            list.add(goods);

            GoodsService goodsService;
            if ((goods.getGoodsStatus() == GoodsStatus.IMPORTED) && (goods.getGoodsTypes() == GoodsTypes.TAXED)) {
                goodsService = new GoodsService(new ImportedAndSalesTaxCalculationService());
                goodsService.calculation(goods);
            } else if ((goods.getGoodsStatus() == GoodsStatus.IMPORTED) && (goods.getGoodsTypes() == GoodsTypes.UNTAXED)) {
                goodsService = new GoodsService(new ImportedTaxCalculationService());
                goodsService.calculation(goods);
            } else if ((goods.getGoodsStatus() == GoodsStatus.NATIONAL) && (goods.getGoodsTypes() == GoodsTypes.TAXED)) {
                goodsService = new GoodsService(new SalesTaxCalculationService());
                goodsService.calculation(goods);
            } else {
                BigDecimal taxedPrice = goods.getPrice();
                BigDecimal tax = BigDecimal.valueOf(0);
                goods.getTaxation().add(new Taxation(taxedPrice, tax));
            }

            for (Taxation taxation: goods.getTaxation()) {
                taxSum = taxSum.add(taxation.getTax());
                priceSum = priceSum.add(taxation.getTaxedPrice());
            }
            System.out.print("Would you like to add some more? Set 1 to 'yes' or any other number to no: ");
            int outValue = scanner.nextInt();

            exit = outValue == 1;
        }while (exit);

        for (Goods object: list){
            for (Taxation taxation: object.getTaxation()){
                    System.out.println(object.getName() + " : " + taxation.getTaxedPrice());
            }
        }

        System.out.println("Sales Taxes: " + taxSum);
        System.out.println("Total: " + priceSum);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input, it's not a number! Only set integer numbers");
        } catch (GoodsInvalidOptionExceptions e) {
            System.out.println(e.getMessage());
        } catch (GoodsPriceLessThanZeroExceptions e) {
            System.out.println(e.getMessage());
        } catch (GoodsNameIsEmptyOrNullExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}