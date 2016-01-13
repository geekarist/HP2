package com.github.geekarist.hp2.bestoffer;

import java.util.List;

public class PercentageDiscount<T extends Item> implements Discount<T> {
    private final int mRate;

    public PercentageDiscount(int rate) {
        mRate = rate;
    }

    @Override
    public double calculate(List<T> items) {
        double discount = 0;
        for (T item : items) {
            discount += (item.getPrice() * mRate / 100.);
        }
        return discount;
    }
}
