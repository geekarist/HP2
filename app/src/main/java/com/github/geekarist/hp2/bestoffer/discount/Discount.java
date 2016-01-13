package com.github.geekarist.hp2.bestoffer.discount;

import com.github.geekarist.hp2.bestoffer.Item;

import java.util.List;

public interface Discount<T extends Item> {
    double calculate(List<T> items);
}
