package app.order.price;

import app.common.enums.Money;
import app.items.Item;
import app.order.OrderItem;

class PayOneTakeMore implements PriceStrategy {
    private final Item item;
    private final Integer quantity;
    private final int more;

    public PayOneTakeMore(OrderItem orderItem, int more) {
        this.item = orderItem.item;
        this.quantity = orderItem.quantity;
        this.more = more;
    }

    @Override
    public Money getPrice() {
        int offerPacks = quantity / more;
        int itemsWithoutOfferPacks = quantity % more;
        int itemsToCharge = offerPacks + itemsWithoutOfferPacks;
        return item.getPrice().times(itemsToCharge);
    }
}
