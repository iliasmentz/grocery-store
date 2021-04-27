package app.order.price;

import app.common.enums.Money;
import app.items.beers.Beer;
import app.order.OrderItem;

public class SixPackDiscount extends DefaultPrice {

    public SixPackDiscount(OrderItem orderItem) {
        super(orderItem);
    }

    @Override
    public Money getPrice() {
        Money price = super.getPrice();

        int sixPacksBought = getSixPacksBought();
        Money discountToApply = calculateDiscount(sixPacksBought);
        price.subtract(discountToApply);

        return price;
    }

    private int getSixPacksBought() {
        return super.quantity / 6;
    }

    private Money calculateDiscount(int sixPacksBought) {
        Beer beer = (Beer) super.item;
        return beer.getPackDiscount().times(sixPacksBought);
    }
}
