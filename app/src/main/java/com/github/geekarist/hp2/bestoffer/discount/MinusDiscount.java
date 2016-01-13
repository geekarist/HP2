package com.github.geekarist.hp2.bestoffer.discount;

import com.github.geekarist.hp2.bestoffer.Item;

import java.util.List;

public class MinusDiscount<T extends Item> implements Discount<T> {
    private final int mAmount;

    public MinusDiscount(int amount) {
        mAmount = amount;
    }

    @Override
    public double calculate(List<T> items) {
        return mAmount;
    }
}
