package com.github.geekarist.hp2.domain;

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
