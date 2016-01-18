package com.github.geekarist.hp2.bestoffer.discount;

import com.github.geekarist.hp2.Book;

import java.util.List;

public class MinusDiscount implements Discount {
    private final int mAmount;

    public MinusDiscount(int amount) {
        mAmount = amount;
    }

    @Override
    public double calculate(List<Book> items) {
        return mAmount;
    }
}
