package com.github.geekarist.hp2.domain.bestoffer.discount.calculation;

import com.github.geekarist.hp2.presentation.ParcelableBook;

import java.util.List;

public interface DiscountCalculation {
    double apply(List<ParcelableBook> items);
}
