package com.github.geekarist.hp2.data;

import com.github.geekarist.hp2.domain.Book;
import com.github.geekarist.hp2.domain.bestoffer.BookDiscountCatalog;
import com.github.geekarist.hp2.domain.bestoffer.discount.Offer;
import com.github.geekarist.hp2.domain.bestoffer.discount.OfferCatalog;
import com.github.geekarist.hp2.domain.bestoffer.discount.OfferCatalogCallback;

import java.util.List;

public class RetrofitOfferCatalog implements OfferCatalog {

    private final BookApi mRetrofitBookApi;

    public RetrofitOfferCatalog(BookApi mRetrofitBookApi) {
        this.mRetrofitBookApi = mRetrofitBookApi;
    }

    @Override
    public void list(List<Book> items, final OfferCatalogCallback.Success success, OfferCatalogCallback.Failure failure) {
        String joinedIsbnList = "";
        for (Book book : items) {
            // TODO: unit test
            if (!"".equals(joinedIsbnList)) {
                joinedIsbnList += ',';
            }
            joinedIsbnList += book.getIsbn();
        }

        mRetrofitBookApi.listCommercialOffers(joinedIsbnList, new BookApi.BookApiCallback<BookDiscountCatalog>() {
            @Override
            public void onResponse(BookDiscountCatalog response) {
                List<Offer> offers = response.getOffers();
                success.onListResult(offers);
            }

            @Override
            public void onFailure(Throwable t) {
                failure.onError(t);
            }
        });
    }

}
