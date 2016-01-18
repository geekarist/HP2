package com.github.geekarist.hp2.bestoffer.discount;

import com.github.geekarist.hp2.bestoffer.Item;

import java.util.List;

public interface DiscountCatalog<T extends Item> {

    void list(List<T> items, DiscountCatalogCallback<T> callback);
}
