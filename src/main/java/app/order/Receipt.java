package app.order;

import app.common.enums.Money;
import app.items.Item;
import app.order.price.PriceStrategy;
import app.order.price.PriceStrategyFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Receipt {
    private static final String NEW_LINE = "\n";
    private static final String TIMES = " x ";
    private static final String PRICE_INDICATOR = " -> ";
    private static final String TOTAL_PREFIX = "Total: ";
    private final List<OrderItem> orderItems;

    public Receipt(List<Item> items) {
        orderItems = createOrderItems(items);
    }

    public String print() {
        return printBreakdown() + NEW_LINE + NEW_LINE + printTotal();
    }

    public String printBreakdown() {
        return orderItems
            .stream()
            .map(this::printBreakdownItem)
            .sorted()
            .collect(Collectors.joining(NEW_LINE));
    }

    private String printBreakdownItem(OrderItem orderItem) {
        return orderItem.item.getId()
            + TIMES
            + orderItem.quantity
            + PRICE_INDICATOR
            + PriceStrategyFactory.make(orderItem).getPrice();
    }

    public String printTotal() {
        return TOTAL_PREFIX + total();
    }

    /**
     * Although this is not used by production code, we expose it in order
     * to be able to make better test. This is a trade-off where we prefer
     * to expose an implementation detail, so we can have our tests focused
     * more on the breakdown instead of how the receipt will be printed.
     *
     * @return all the order items
     */
//    public List<OrderItem> getOrderItems() {
//        return orderItems;
//    }

    private List<OrderItem> createOrderItems(List<Item> items) {
        return groupByItemId(items)
            .values()
            .stream()
            .map(this::toOrderItem)
            .collect(Collectors.toList());
    }

    /**
     * Although this is not used by production code, we expose it in order
     * to be able to make better test. This is a trade-off where we prefer
     * to expose an implementation detail, so we can have our tests focused
     * more on the breakdown instead of how the receipt will be printed.
     *
     * @return the total sum of the order
     */
    public Money total() {
        Money money = new Money();
        orderItems.stream()
            .map(PriceStrategyFactory::make)
            .map(PriceStrategy::getPrice).forEach(money::add);
        return money;
    }

    private Map<String, List<Item>> groupByItemId(List<Item> items) {
        return items
            .stream()
            .collect(Collectors.groupingBy(Item::getId));
    }

    private OrderItem toOrderItem(List<Item> items) {
        return new OrderItem(getAny(items), items.size());
    }

    private Item getAny(List<Item> items) {
        return items.get(0);
    }
}
