package com.github.geekarist.hp2.bestoffer.discount;

import android.util.Log;

import com.github.geekarist.hp2.Book;

import java.util.List;

public class BookDiscount implements DiscountCalculation {
    private static final String TAG = BookDiscount.class.getSimpleName();

    String type;
    int value;
    int sliceValue;

    @Override
    public String toString() {
        return "BookDiscount{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", sliceValue='" + sliceValue + '\'' +
                '}';
    }

    @Override
    public double apply(List<Book> items) {
        DiscountCalculation discount;
        if ("minus".equals(type)) {
            discount = new MinusDiscountCalculation(value);
        } else if ("percentage".equals(type)) {
            discount = new PercentageDiscountCalculation(value);
        } else if ("slice".equals(type)) {
            discount = new SliceDiscountCalculation(value, sliceValue);
        } else {
            Log.w(TAG, String.format("Unknown offer [%s]", type));
            return 0;
        }
        return discount.apply(items);
    }
}
