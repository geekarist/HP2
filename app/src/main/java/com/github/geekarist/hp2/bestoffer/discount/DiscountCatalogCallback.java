package com.github.geekarist.hp2.bestoffer.discount;

import java.util.List;

public interface DiscountCatalogCallback {
    void onListResult(List<BookDiscount> discounts);
}
