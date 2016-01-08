package com.github.geekarist.hp2.bestoffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FakeDiscountCatalog<T extends Item> implements DiscountCatalog<T> {
    @Override
    public List<Discount<T>> list() {
        return Arrays.asList(new Discount<T>() {
            @Override
            public double calculate(List items) {
                return 5;
            }
        }, new Discount<T>() {
            @Override
            public double calculate(List items) {
                return 9;
            }
        });
    }
}
