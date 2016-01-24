package com.github.geekarist.hp2;

import com.github.geekarist.hp2.bestoffer.discount.BookDiscount;

import java.util.List;

class BookDiscountCatalog {
    List<BookDiscount> offers;

    @Override
    public String toString() {
        return "BookDiscountCatalog{" +
                "offers=" + offers +
                '}';
    }
}
