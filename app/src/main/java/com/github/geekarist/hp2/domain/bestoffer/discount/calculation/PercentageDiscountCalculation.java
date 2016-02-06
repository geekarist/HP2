package com.github.geekarist.hp2.domain.bestoffer.discount.calculation;

import com.github.geekarist.hp2.presentation.ParcelableBook;

import java.util.List;

public class PercentageDiscountCalculation implements DiscountCalculation {
    private final int mRate;

    public PercentageDiscountCalculation(int rate) {
        mRate = rate;
    }

    @Override
    public double apply(List<ParcelableBook> items) {
        double discount = 0;
        for (ParcelableBook item : items) {
            discount += (item.getPrice() * mRate / 100.);
        }
        return discount;
    }
}
