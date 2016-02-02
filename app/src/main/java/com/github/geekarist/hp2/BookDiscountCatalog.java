package com.github.geekarist.hp2;

import com.github.geekarist.hp2.bestoffer.discount.Offer;

import java.util.ArrayList;
import java.util.List;

class BookDiscountCatalog {
    List<Offer> offers = new ArrayList<>();

    public BookDiscountCatalog(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "BookDiscountCatalog{" +
                "offers=" + offers +
                '}';
    }
}
