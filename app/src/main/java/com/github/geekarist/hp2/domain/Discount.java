package com.github.geekarist.hp2.domain;

import java.util.List;

public interface Discount {
    double calculate(List<Book> items);
}
