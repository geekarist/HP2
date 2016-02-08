package com.github.geekarist.hp2.domain.bestoffer.discount;

import com.github.geekarist.hp2.presentation.ParcelableBook;

import java.util.List;

public interface OfferCatalog {

    void list(List<ParcelableBook> items, OfferCatalogCallback.Success callback, OfferCatalogCallback.Failure failure);
}
