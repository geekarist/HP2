package com.github.geekarist.hp2;

import android.support.annotation.NonNull;

import com.github.geekarist.hp2.bestoffer.discount.BookDiscount;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalogCallback;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DiscountCatalogTest {

    @Test
    public void testList() throws Exception {
        BookApi fakeBookApi = newFakeBookApi(new BookDiscountCatalog());
        DiscountCatalog discountCatalog = new DiscountCatalog(fakeBookApi);

        discountCatalog.list(new ArrayList<Book>(), new DiscountCatalogCallback() {
            @Override
            public void onListResult(List<BookDiscount> discounts) {
                Assert.fail();
            }
        });
    }

    @NonNull
    private BookApi newFakeBookApi(BookDiscountCatalog catalog) {
        final BookDiscountCatalog finalCatalog = catalog;
        return new BookApi() {
            @Override
            public void listCommercialOffers(String joinedIsbnList, BookApiCallback callback) {
                callback.onResponse(finalCatalog);
            }
        };
    }

}