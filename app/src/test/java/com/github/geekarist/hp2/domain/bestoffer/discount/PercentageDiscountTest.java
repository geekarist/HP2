package com.github.geekarist.hp2.domain.bestoffer.discount;

import android.support.annotation.NonNull;

import com.github.geekarist.hp2.domain.Book;
import com.github.geekarist.hp2.domain.bestoffer.discount.calculation.DiscountCalculation;
import com.github.geekarist.hp2.domain.bestoffer.discount.calculation.PercentageDiscountCalculation;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PercentageDiscountTest {

    @Test
    public void shouldCalculate() throws Exception {
        DiscountCalculation percentageDiscount = new PercentageDiscountCalculation(5);
        List<Book> items = Arrays.asList(newBook(100), newBook(150));

        double discount = percentageDiscount.apply(items);

        Assert.assertThat(discount, new IsEqual<>(12.5));
    }

    @NonNull
    private Book newBook(int price) {
        return new Book(null, null, price, null);
    }
}