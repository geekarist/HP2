package com.github.geekarist.hp2.bestoffer;

import java.util.List;

public interface DiscountCatalog<T extends Item> {
    List<Discount<T>> list();
}
