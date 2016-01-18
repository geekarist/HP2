package com.github.geekarist.hp2.bestoffer.discount;

import com.github.geekarist.hp2.bestoffer.Item;

import java.util.List;

public interface DiscountCatalogCallback<T extends Item> {
    void onListResult(List<Discount<T>> discounts);
}
