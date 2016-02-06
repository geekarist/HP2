package com.github.geekarist.hp2.domain.bestoffer.discount;

import java.util.List;

public interface OfferCatalogCallback {
    void onListResult(List<Offer> discounts);
}
