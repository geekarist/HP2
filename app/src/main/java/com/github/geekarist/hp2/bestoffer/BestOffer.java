package com.github.geekarist.hp2.bestoffer;

import com.github.geekarist.hp2.Book;
import com.github.geekarist.hp2.bestoffer.discount.Offer;
import com.github.geekarist.hp2.bestoffer.discount.OfferCatalog;

import java.util.List;

public class BestOffer {
    private final Cart cart;
    private final OfferCatalog mOfferCatalog;

    public BestOffer(Cart cart, OfferCatalog offerCatalog) {
        this.cart = cart;
        this.mOfferCatalog = offerCatalog;
    }

    public void calculate(final BestOffer.Callback callback) {
        mOfferCatalog.list(cart.getItems(), discounts -> {
            double maxAmount = 0;
            for (Offer discount : discounts) {
                List<Book> items = cart.getItems();
                double amount = discount.apply(items);
                if (amount > maxAmount) {
                    maxAmount = amount;
                }
            }
            callback.apply(maxAmount);
        });
    }

    public interface Callback {
        void apply(double bestValue);
    }
}
