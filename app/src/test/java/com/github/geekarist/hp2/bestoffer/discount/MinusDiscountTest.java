package com.github.geekarist.hp2.bestoffer.discount;

import android.support.annotation.NonNull;

import com.github.geekarist.hp2.Book;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MinusDiscountTest {
    @Test
    public void shouldCalculate() throws Exception {
        Discount discount = new MinusDiscount(15);
        List<Book> itemList = Arrays.asList(newBook(35), newBook(30));

        double amount = discount.calculate(itemList);

        Assert.assertThat(amount, new IsEqual<>(15.));
    }

    @NonNull
    private Book newBook(int price) {
        return new Book(null, null, price, null);
    }
}