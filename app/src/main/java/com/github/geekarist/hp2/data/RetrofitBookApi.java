package com.github.geekarist.hp2.data;

import com.github.geekarist.hp2.domain.bestoffer.BookDiscountCatalog;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RetrofitBookApi implements BookApi {
    private final BookService mBookService;

    public RetrofitBookApi(BookService bookService) {
        mBookService = bookService;
    }

    @Override
    public void listCommercialOffers(String joinedIsbnList, final BookApiCallback callback) {
        mBookService.listCommercialOffers(joinedIsbnList).enqueue(new Callback<BookDiscountCatalog>() {
            @Override
            public void onResponse(Response<BookDiscountCatalog> response, Retrofit retrofit) {
                callback.onResponse(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
