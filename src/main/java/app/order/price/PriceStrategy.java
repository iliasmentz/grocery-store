package app.order.price;

import app.common.enums.Money;

public interface PriceStrategy {
    Money getPrice();
}
