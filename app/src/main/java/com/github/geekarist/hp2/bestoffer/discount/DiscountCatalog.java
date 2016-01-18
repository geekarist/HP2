package com.github.geekarist.hp2.bestoffer.discount;

import com.github.geekarist.hp2.Book;

import java.util.List;

public interface DiscountCatalog {

    void list(List<Book> items, DiscountCatalogCallback callback);
}
