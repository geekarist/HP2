package com.github.geekarist.hp2.domain.bestoffer.discount;

import android.support.annotation.NonNull;

import com.github.geekarist.hp2.domain.bestoffer.discount.calculation.DiscountCalculation;
import com.github.geekarist.hp2.domain.bestoffer.discount.calculation.SliceDiscountCalculation;
import com.github.geekarist.hp2.presentation.ParcelableBook;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SliceDiscountTest {
    @Test
    public void shouldCalculate() throws Exception {
        DiscountCalculation discount = new SliceDiscountCalculation(12, 100);
        List<ParcelableBook> itemList = Arrays.asList(newBook(29), newBook(42), newBook(53), newBook(72), newBook(122));

        double amount = discount.apply(itemList);

        Assert.assertThat(amount, new IsEqual<>(3. * 12));
    }

    @NonNull
    private ParcelableBook newBook(int price) {
        return new ParcelableBook(null, null, price, null);
    }
}