package com.github.geekarist.hp2.domain.bestoffer.discount;

import com.github.geekarist.hp2.domain.Book;

import java.util.List;

public interface OfferCatalog {

    void list(List<Book> items, OfferCatalogCallback.Success callback, OfferCatalogCallback.Failure failure);
}
