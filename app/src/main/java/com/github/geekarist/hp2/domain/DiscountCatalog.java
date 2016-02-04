package com.github.geekarist.hp2.domain;

import java.util.List;

public interface DiscountCatalog {

    void list(List<Book> items, DiscountCatalogCallback callback);
}
