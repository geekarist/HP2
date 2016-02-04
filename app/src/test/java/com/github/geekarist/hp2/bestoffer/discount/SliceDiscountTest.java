package com.github.geekarist.hp2.bestoffer.discount;

import android.support.annotation.NonNull;

import com.github.geekarist.hp2.domain.Book;
import com.github.geekarist.hp2.domain.Discount;
import com.github.geekarist.hp2.domain.SliceDiscount;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SliceDiscountTest {
    @Test
    public void shouldCalculate() throws Exception {
        Discount discount = new SliceDiscount(12, 100);
        List<Book> itemList = Arrays.asList(newBook(29), newBook(42), newBook(53), newBook(72), newBook(122));

        double amount = discount.calculate(itemList);

        Assert.assertThat(amount, new IsEqual<>(3. * 12));
    }

    @NonNull
    private Book newBook(int price) {
        return new Book(null, null, price, null);
    }
}