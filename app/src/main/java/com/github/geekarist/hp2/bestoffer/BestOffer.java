package com.github.geekarist.hp2.bestoffer;

import com.github.geekarist.hp2.bestoffer.discount.Discount;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalog;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalogCallback;

import java.util.List;

public class BestOffer<T extends Item> {
    private final Cart<T> cart;
    private final DiscountCatalog<T> discountCatalog;

    public BestOffer(Cart<T> cart, DiscountCatalog<T> discountCatalog) {
        this.cart = cart;
        this.discountCatalog = discountCatalog;
    }

    public void calculate(final BestOffer.Callback callback) {
        discountCatalog.list(cart.getItems(), new DiscountCatalogCallback<T>() {
            @Override
            public void onListResult(List<Discount<T>> discounts) {
                double maxAmount = 0;
                for (Discount<T> discount : discounts) {
                    List<T> items = cart.getItems();
                    double amount = discount.calculate(items);
                    if (amount > maxAmount) {
                        maxAmount = amount;
                    }
                }
                callback.apply(maxAmount);
            }
        });
    }

    public interface Callback {
        void apply(double bestValue);
    }
}
