package com.github.geekarist.hp2.bestoffer.discount.calculation;

import com.github.geekarist.hp2.Book;

import java.util.List;

public class MinusDiscountCalculation implements DiscountCalculation {
    private final int mAmount;

    public MinusDiscountCalculation(int amount) {
        mAmount = amount;
    }

    @Override
    public double apply(List<Book> items) {
        return mAmount;
    }
}
