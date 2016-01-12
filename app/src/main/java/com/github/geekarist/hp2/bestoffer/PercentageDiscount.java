package com.github.geekarist.hp2.bestoffer;

import java.util.List;

public class PercentageDiscount implements Discount<Item> {
    private final int mRate;

    public PercentageDiscount(int rate) {
        mRate = rate;
    }

    @Override
    public double calculate(List<Item> items) {
        double discount = 0;
        for (Item item : items) {
            discount += (item.getPrice() * mRate / 100.);
        }
        return discount;
    }
}
