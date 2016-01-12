package com.github.geekarist.hp2.bestoffer;

import android.support.annotation.NonNull;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PercentageDiscountTest {

    @Test
    public void shouldCalculate() throws Exception {
        Discount<Item> percentageDiscount = new PercentageDiscount(5);
        List<Item> items = Arrays.asList(item(100), item(150));

        double discount = percentageDiscount.calculate(items);

        Assert.assertThat(discount, new IsEqual<>(12.5));
    }

    @NonNull
    private Item item(final double price) {
        return new Item() {
            @Override
            public double getPrice() {
                return price;
            }
        };
    }
}