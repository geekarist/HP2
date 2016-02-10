package com.github.geekarist.hp2.data;

import com.github.geekarist.hp2.domain.Book;
import com.github.geekarist.hp2.domain.bestoffer.BookDiscountCatalog;

import java.util.List;

public interface BookApi {
    void listCommercialOffers(String joinedIsbnList, BookApiCallback<BookDiscountCatalog> callback);

    void listBooks(BookApiCallback<List<Book>> callback);

    interface BookApiCallback<T> {
        void onResponse(T response);

        void onFailure(Throwable t);
    }
}
