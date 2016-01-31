package com.github.geekarist.hp2;

import com.github.geekarist.hp2.bestoffer.discount.BookDiscount;

import java.util.ArrayList;
import java.util.List;

class BookDiscountCatalog {
    List<BookDiscount> offers = new ArrayList<>();

    public BookDiscountCatalog(List<BookDiscount> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "BookDiscountCatalog{" +
                "offers=" + offers +
                '}';
    }
}
