package app.order;

import app.items.Item;

public class OrderItem {
    public Item item;
    public Integer quantity;

    public OrderItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
