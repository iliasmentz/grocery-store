package app.items;

import app.common.enums.Money;

public interface Item {
    String getId();

    ItemType getType();

    Money getPrice();

    void validate();
}
