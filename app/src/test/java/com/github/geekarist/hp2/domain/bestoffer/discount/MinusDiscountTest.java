package com.github.geekarist.hp2.domain.bestoffer.discount;

import android.support.annotation.NonNull;

import com.github.geekarist.hp2.domain.bestoffer.discount.calculation.DiscountCalculation;
import com.github.geekarist.hp2.domain.bestoffer.discount.calculation.MinusDiscountCalculation;
import com.github.geekarist.hp2.presentation.ParcelableBook;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MinusDiscountTest {
    @Test
    public void shouldCalculate() throws Exception {
        DiscountCalculation discount = new MinusDiscountCalculation(15);
        List<ParcelableBook> itemList = Arrays.asList(newBook(35), newBook(30));

        double amount = discount.apply(itemList);

        Assert.assertThat(amount, new IsEqual<>(15.));
    }

    @NonNull
    private ParcelableBook newBook(int price) {
        return new ParcelableBook(null, null, price, null);
    }
}