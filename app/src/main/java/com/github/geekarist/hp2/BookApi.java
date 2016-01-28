package com.github.geekarist.hp2;

public interface BookApi {
    void listCommercialOffers(String joinedIsbnList, BookApiCallback callback);

    interface BookApiCallback {
        void onResponse(BookDiscountCatalog catalog);

        void onFailure(Throwable t);
    }
}
