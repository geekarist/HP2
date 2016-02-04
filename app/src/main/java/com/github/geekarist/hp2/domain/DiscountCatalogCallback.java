package com.github.geekarist.hp2.domain;

import java.util.List;

public interface DiscountCatalogCallback {
    void onListResult(List<Discount> discounts);
}
