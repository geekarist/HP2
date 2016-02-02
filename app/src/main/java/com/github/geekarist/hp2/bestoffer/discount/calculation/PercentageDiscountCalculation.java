package com.github.geekarist.hp2.bestoffer.discount.calculation;

import com.github.geekarist.hp2.Book;

import java.util.List;

public class PercentageDiscountCalculation implements DiscountCalculation {
    private final int mRate;

    public PercentageDiscountCalculation(int rate) {
        mRate = rate;
    }

    @Override
    public double apply(List<Book> items) {
        double discount = 0;
        for (Book item : items) {
            discount += (item.getPrice() * mRate / 100.);
        }
        return discount;
    }
}
