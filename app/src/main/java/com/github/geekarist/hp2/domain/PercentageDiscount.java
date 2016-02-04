package com.github.geekarist.hp2.domain;

import java.util.List;

public class PercentageDiscount implements Discount {
    private final int mRate;

    public PercentageDiscount(int rate) {
        mRate = rate;
    }

    @Override
    public double calculate(List<Book> items) {
        double discount = 0;
        for (Book item : items) {
            discount += (item.getPrice() * mRate / 100.);
        }
        return discount;
    }
}
