package com.github.geekarist.hp2.domain.bestoffer;

import com.github.geekarist.hp2.domain.bestoffer.discount.Offer;

import java.util.ArrayList;
import java.util.List;

public class BookDiscountCatalog {
    List<Offer> offers = new ArrayList<>();

    public BookDiscountCatalog(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    @Override
    public String toString() {
        return "BookDiscountCatalog{" +
                "offers=" + offers +
                '}';
    }
}
