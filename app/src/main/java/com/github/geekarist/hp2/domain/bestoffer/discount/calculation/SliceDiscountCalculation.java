package com.github.geekarist.hp2.domain.bestoffer.discount.calculation;

import com.github.geekarist.hp2.domain.Book;

import java.util.List;

public class SliceDiscountCalculation implements DiscountCalculation {
    private final int mValue;
    private final int mSliceValue;

    public SliceDiscountCalculation(int value, int sliceValue) {
        mValue = value;
        mSliceValue = sliceValue;
    }

    @Override
    public double apply(List<Book> items) {
        double sum = 0;
        for (Book item : items) {
            sum += item.getPrice();
        }
        return (int) (sum / mSliceValue) * mValue;
    }
}
