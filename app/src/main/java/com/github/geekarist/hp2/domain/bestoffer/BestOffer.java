package com.github.geekarist.hp2.domain.bestoffer;

import com.github.geekarist.hp2.domain.bestoffer.discount.Offer;
import com.github.geekarist.hp2.domain.bestoffer.discount.OfferCatalog;
import com.github.geekarist.hp2.presentation.ParcelableBook;

import java.util.List;

public class BestOffer {
    private final Cart cart;
    private final OfferCatalog mOfferCatalog;

    public BestOffer(Cart cart, OfferCatalog offerCatalog) {
        this.cart = cart;
        this.mOfferCatalog = offerCatalog;
    }

    public void calculate(final SuccessCallback successCallback, final FailureCallback failure) {
        mOfferCatalog.list(cart.getItems(), discounts -> {
            double maxAmount = 0;
            for (Offer discount : discounts) {
                List<ParcelableBook> items = cart.getItems();
                double amount = discount.apply(items);
                if (amount > maxAmount) {
                    maxAmount = amount;
                }
            }
            successCallback.apply(maxAmount);
        }, failure::handle);
    }

    public interface SuccessCallback {
        void apply(double bestValue);
    }

    public interface FailureCallback {
        void handle(Throwable t);
    }
}
