package com.github.geekarist.hp2.bestoffer;

import java.util.List;

public class BestOffer<T extends Item> {
    private final Cart<T> cart;
    private final DiscountCatalog<T> discountCatalog;

    public BestOffer(Cart<T> cart, DiscountCatalog<T> discountCatalog) {
        this.cart = cart;
        this.discountCatalog = discountCatalog;
    }

    public double calculate() {
        List<Discount<T>> discountList = discountCatalog.list();
        double maxAmount = 0;
        for (Discount<T> discount : discountList) {
            List<T> items = cart.getItems();
            double amount = discount.calculate(items);
            if (amount > maxAmount) {
                maxAmount = amount;
            }
        }
        return maxAmount;
    }
}
