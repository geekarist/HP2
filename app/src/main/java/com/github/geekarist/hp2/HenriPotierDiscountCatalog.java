package com.github.geekarist.hp2;

import com.github.geekarist.hp2.bestoffer.Discount;
import com.github.geekarist.hp2.bestoffer.PercentageDiscount;

import java.util.Collections;
import java.util.List;

public class HenriPotierDiscountCatalog implements com.github.geekarist.hp2.bestoffer.DiscountCatalog<Book> {
    @Override
    public List<Discount<Book>> list() {
        Discount<Book> percentageDiscount = new PercentageDiscount<>(5);
        return Collections.singletonList(percentageDiscount);
    }
}
