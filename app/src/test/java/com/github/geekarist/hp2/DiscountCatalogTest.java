package com.github.geekarist.hp2;

import android.support.annotation.NonNull;

import com.github.geekarist.hp2.bestoffer.discount.BookDiscount;

import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class DiscountCatalogTest {

    @Test
    public void testList() throws Exception {
        // Given
        BookApi fakeBookApi = newFakeBookApi(newFakeDiscountCatalog());
        DiscountCatalog discountCatalog = new DiscountCatalog(fakeBookApi);

        // When
        discountCatalog.list(new ArrayList<>(), discounts -> {

            // Then
            assertThat(discounts).containsOnlyElementsOf(
                    asList(new BookDiscount("slice", 5, 100), new BookDiscount("percentage", 5, 0), new BookDiscount("minus", 5, 0)));
        });
    }

    @NonNull
    private BookDiscountCatalog newFakeDiscountCatalog() {
        BookDiscountCatalog bookDiscountCatalog = new BookDiscountCatalog();
        bookDiscountCatalog.offers.addAll(
                asList(new BookDiscount("slice", 5, 100), new BookDiscount("percentage", 5, 0), new BookDiscount("minus", 5, 0)));
        return bookDiscountCatalog;
    }

    @NonNull
    private BookApi newFakeBookApi(BookDiscountCatalog catalog) {
        return (joinedIsbnList, callback) -> callback.onResponse(catalog);
    }

}