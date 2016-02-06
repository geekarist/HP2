package com.github.geekarist.hp2.data;

import android.util.Log;

import com.github.geekarist.hp2.domain.bestoffer.BookDiscountCatalog;
import com.github.geekarist.hp2.domain.bestoffer.discount.Offer;
import com.github.geekarist.hp2.domain.bestoffer.discount.OfferCatalog;
import com.github.geekarist.hp2.domain.bestoffer.discount.OfferCatalogCallback;
import com.github.geekarist.hp2.presentation.ParcelableBook;

import java.util.List;

public class RetrofitOfferCatalog implements OfferCatalog {

    private static final String TAG = RetrofitOfferCatalog.class.getSimpleName();

    private final BookApi mRetrofitBookApi;

    public RetrofitOfferCatalog(BookApi mRetrofitBookApi) {
        this.mRetrofitBookApi = mRetrofitBookApi;
    }

    public RetrofitOfferCatalog(BookService bookService) {
        this(new RetrofitBookApi(bookService));
    }

    @Override
    public void list(List<ParcelableBook> items, final OfferCatalogCallback callback) {
        String joinedIsbnList = "";
        for (ParcelableBook book : items) {
            // TODO: unit test
            if (!"".equals(joinedIsbnList)) {
                joinedIsbnList += ',';
            }
            joinedIsbnList += book.getIsbn();
        }

        mRetrofitBookApi.listCommercialOffers(joinedIsbnList, new BookApi.BookApiCallback() {
            @Override
            public void onResponse(BookDiscountCatalog response) {
                List<Offer> offers = response.getOffers();
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
