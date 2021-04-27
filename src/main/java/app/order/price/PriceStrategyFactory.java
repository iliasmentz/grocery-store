package app.order.price;

import app.items.Bread;
import app.items.Item;
import app.order.OrderItem;

public class PriceStrategyFactory {

    private static final int SIX_PACK = 6;
    private static final int SIX_DAYS = 6;
    private static final int TWO = 2;

    public static PriceStrategy make(OrderItem orderItem) {
        Item item = orderItem.item;

        return switch (item.getType()) {
            case BEER -> makeBeerStrategy(orderItem);
            case BREAD -> makeBreadStrategy(orderItem);
        };
    }

    private static PriceStrategy makeBeerStrategy(OrderItem orderItem) {
        if (applicableSixPackDiscount(orderItem)) {
            return new SixPackDiscount(orderItem);
        }
        return new DefaultPrice(orderItem);
    }

    private static boolean applicableSixPackDiscount(OrderItem orderItem) {
        return orderItem.quantity >= SIX_PACK;
    }

    private static PriceStrategy makeBreadStrategy(OrderItem orderItem) {
        if (applicableForPayOneTakeTwo(orderItem)) {
            return new PayOneTakeMore(orderItem, 2);
        } else if (applicableForPayOneTakeThree(orderItem)) {
            return new PayOneTakeMore(orderItem, 3);
        }
        return new DefaultPrice(orderItem);
    }

    private static boolean applicableForPayOneTakeTwo(OrderItem orderItem) {
        Bread item = (Bread) orderItem.item;
        return item.ageInDays() > TWO
            && item.ageInDays() < SIX_DAYS
            && orderItem.quantity >= TWO;
    }

    private static boolean applicableForPayOneTakeThree(OrderItem orderItem) {
        Bread item = (Bread) orderItem.item;
        return item.ageInDays() >= SIX_DAYS
            && orderItem.quantity > TWO;
    }
}
