package app.order.price;

import app.common.enums.Money;
import app.items.Item;
import app.order.OrderItem;

class DefaultPrice implements PriceStrategy {
    protected Item item;
    protected Integer quantity;

    public DefaultPrice(OrderItem orderItem) {
        this.item = orderItem.item;
        this.quantity = orderItem.quantity;
    }

    @Override
    public Money getPrice() {
        return item.getPrice().times(quantity);
    }
}
