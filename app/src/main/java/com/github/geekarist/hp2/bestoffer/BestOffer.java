package com.github.geekarist.hp2.bestoffer;

import com.github.geekarist.hp2.Book;
import com.github.geekarist.hp2.bestoffer.discount.BookDiscount;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalog;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalogCallback;

import java.util.List;

public class BestOffer {
    private final Cart cart;
    private final DiscountCatalog discountCatalog;

    public BestOffer(Cart cart, DiscountCatalog discountCatalog) {
        this.cart = cart;
        this.discountCatalog = discountCatalog;
    }

    public void calculate(final BestOffer.Callback callback) {
        discountCatalog.list(cart.getItems(), new DiscountCatalogCallback() {
            @Override
            public void onListResult(List<BookDiscount> discounts) {
                double maxAmount = 0;
                for (BookDiscount discount : discounts) {
                    List<Book> items = cart.getItems();
                    double amount = discount.apply(items);
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
