package com.github.geekarist.hp2;

import com.github.geekarist.hp2.bestoffer.discount.BookDiscount;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalogCallback;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class DiscountCatalogTest {

    @Test
    public void testList() throws Exception {
        BookService bookServiceMock = Mockito.mock(BookService.class);
        DiscountCatalog discountCatalog = new DiscountCatalog(bookServiceMock);

        discountCatalog.list(new ArrayList<Book>(), new DiscountCatalogCallback() {
            @Override
            public void onListResult(List<BookDiscount> discounts) {
                Assert.fail();
            }
        });
    }
}