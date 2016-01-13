package com.github.geekarist.hp2;

import com.github.geekarist.hp2.bestoffer.discount.Discount;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalog;
import com.github.geekarist.hp2.bestoffer.discount.MinusDiscount;
import com.github.geekarist.hp2.bestoffer.discount.PercentageDiscount;

import java.util.Arrays;
import java.util.List;

public class HenriPotierDiscountCatalog implements DiscountCatalog<Book> {
    @Override
    public List<Discount<Book>> list() {
        Discount<Book> percentageDiscount = new PercentageDiscount<>(5);
        Discount<Book> minusDiscount = new MinusDiscount<>(15);
        return Arrays.asList(percentageDiscount, minusDiscount);
    }
}
