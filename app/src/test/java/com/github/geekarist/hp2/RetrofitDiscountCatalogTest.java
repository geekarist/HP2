package com.github.geekarist.hp2;

import com.github.geekarist.hp2.bestoffer.discount.BookDiscount;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalog;

import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class RetrofitDiscountCatalogTest {

    @Test
    public void shouldListDiscounts() throws Exception {
        // Given
        DiscountCatalog discountCatalog = new RetrofitDiscountCatalog((joinedIsbnList, callback) ->
                callback.onResponse(new BookDiscountCatalog(asList(
                        new BookDiscount("slice", 5, 100),
                        new BookDiscount("percentage", 5, 0),
                        new BookDiscount("minus", 5, 0)))));

        // When
        discountCatalog.list(new ArrayList<>(), discounts -> {

            // Then
            assertThat(discounts).containsOnlyElementsOf(
                    asList(new BookDiscount("slice", 5, 100), new BookDiscount("percentage", 5, 0), new BookDiscount("minus", 5, 0)));
        });
    }

}