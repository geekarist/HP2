package com.github.geekarist.hp2.bestoffer;

import java.util.List;

public interface Cart<T extends Item> {
    List<T> getItems();
}
