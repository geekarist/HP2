package com.github.geekarist.hp2.bestoffer.discount;

import android.support.annotation.NonNull;

import com.github.geekarist.hp2.bestoffer.Item;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MinusDiscountTest {
    @Test
    public void shouldCalculate() throws Exception {
        Discount<Item> discount = new MinusDiscount<>(15);
        List<Item> itemList = Arrays.asList(item(35), item(30));

        double amount = discount.calculate(itemList);

        Assert.assertThat(amount, new IsEqual<>(15.));
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