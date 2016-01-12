package com.github.geekarist.hp2.bestoffer;

import com.github.geekarist.hp2.Book;

import java.util.List;

public interface Discount<T extends Item> {
    double calculate(List<T> items);
}
