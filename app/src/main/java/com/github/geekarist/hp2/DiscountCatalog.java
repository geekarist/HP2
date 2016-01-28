package com.github.geekarist.hp2;

import android.util.Log;

import com.github.geekarist.hp2.bestoffer.discount.BookDiscount;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalogCallback;

import java.util.List;

public class DiscountCatalog implements com.github.geekarist.hp2.bestoffer.discount.DiscountCatalog {

    private static final String TAG = DiscountCatalog.class.getSimpleName();

    private final BookApi mRetrofitBookApi;

    public DiscountCatalog(BookApi mRetrofitBookApi) {
        this.mRetrofitBookApi = mRetrofitBookApi;
    }

    public DiscountCatalog(BookService bookService) {
        this(new RetrofitBookApi(bookService));
    }

    // TODO: unit test
    @Override
    public void list(List<Book> items, final DiscountCatalogCallback callback) {
        String joinedIsbnList = "";
        for (Book book : items) {
            if (!"".equals(joinedIsbnList)) {
                joinedIsbnList += ',';
            }
            joinedIsbnList += book.isbn;
        }

        mRetrofitBookApi.listCommercialOffers(joinedIsbnList, new BookApi.BookApiCallback() {
            @Override
            public void onResponse(BookDiscountCatalog response) {
                List<BookDiscount> offers = response.offers;
                callback.onListResult(offers);
            }

            @Override
            public void onFailure(Throwable t) {
                // TODO: display error
                Log.e(TAG, "Error while listing commercial offers", t);
            }
        });
    }

}
