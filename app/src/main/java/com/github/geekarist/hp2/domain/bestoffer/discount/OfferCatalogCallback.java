package com.github.geekarist.hp2.domain.bestoffer.discount;

import java.util.List;

public interface OfferCatalogCallback {
    // TODO use Consumer<List<Offer>> instead
    interface Success {
        void onListResult(List<Offer> discounts);
    }

    interface Failure {
        void onError(Throwable t);
    }
}
