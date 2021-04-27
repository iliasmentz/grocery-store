package app.order;

import app.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Item> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        item.validate();
        items.add(item);
    }

    public Receipt receipt() {
        return new Receipt(items);
    }

}
