package com.github.geekarist.hp2.bestoffer.discount;

import com.github.geekarist.hp2.Book;

import java.util.List;

public interface OfferCatalog {

    void list(List<Book> items, OfferCatalogCallback callback);
}
