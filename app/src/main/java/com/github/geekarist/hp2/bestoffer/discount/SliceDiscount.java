package com.github.geekarist.hp2.bestoffer.discount;

import com.github.geekarist.hp2.Book;

import java.util.List;

public class SliceDiscount implements Discount {
    private final int mValue;
    private final int mSliceValue;

    public SliceDiscount(int value, int sliceValue) {
        mValue = value;
        mSliceValue = sliceValue;
    }

    @Override
    public double calculate(List<Book> items) {
        double sum = 0;
        for (Book item : items) {
            sum += item.getPrice();
        }
        return (int) (sum / mSliceValue) * mValue;
    }
}
