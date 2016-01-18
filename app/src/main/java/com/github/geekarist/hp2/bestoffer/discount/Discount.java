package com.github.geekarist.hp2.bestoffer.discount;

import com.github.geekarist.hp2.Book;

import java.util.List;

public interface Discount {
    double calculate(List<Book> items);
}
