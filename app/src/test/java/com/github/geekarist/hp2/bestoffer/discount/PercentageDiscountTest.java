package com.github.geekarist.hp2.bestoffer.discount;

import android.support.annotation.NonNull;

import com.github.geekarist.hp2.Book;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PercentageDiscountTest {

    @Test
    public void shouldCalculate() throws Exception {
        Discount percentageDiscount = new PercentageDiscount(5);
        List<Book> items = Arrays.asList(newBook(100), newBook(150));

        double discount = percentageDiscount.calculate(items);

        Assert.assertThat(discount, new IsEqual<>(12.5));
    }

    @NonNull
    private Book newBook(int price) {
        return new Book(null, null, price, null);
    }
}