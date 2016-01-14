package com.github.geekarist.hp2.bestoffer.discount;

import com.github.geekarist.hp2.bestoffer.Item;

import java.util.List;

public class SliceDiscount<T extends Item> implements Discount<T> {
    private final int mValue;
    private final int mSliceValue;

    public SliceDiscount(int value, int sliceValue) {
        mValue = value;
        mSliceValue = sliceValue;
    }

    @Override
    public double calculate(List<T> items) {
        double sum = 0;
        for (T item : items) {
            sum += item.getPrice();
        }
        return (int) (sum / mSliceValue) * mValue;
    }
}
