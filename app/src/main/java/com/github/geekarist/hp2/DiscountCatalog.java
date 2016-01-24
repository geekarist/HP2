package com.github.geekarist.hp2;

import android.util.Log;

import com.github.geekarist.hp2.bestoffer.discount.BookDiscount;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalogCallback;

import java.util.List;

import retrofit.Response;
import retrofit.Retrofit;

public class DiscountCatalog implements com.github.geekarist.hp2.bestoffer.discount.DiscountCatalog {

    private static final String TAG = DiscountCatalog.class.getSimpleName();

    private final BookService mBookService;

    public DiscountCatalog(BookService bookService) {
        mBookService = bookService;
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

        mBookService.listCommercialOffers(joinedIsbnList).enqueue(new retrofit.Callback<BookDiscountCatalog>() {
            @Override
            public void onResponse(Response<BookDiscountCatalog> response, Retrofit retrofit) {
                List<BookDiscount> offers = response.body().offers;
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
