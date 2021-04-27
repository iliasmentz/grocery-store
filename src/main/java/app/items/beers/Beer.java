package app.items.beers;

import app.common.enums.Country;
import app.common.enums.Money;
import app.items.Item;
import app.items.ItemType;

public class Beer implements Item {
    private Country origin;
    private Money price;
    private Money packDiscount;

    Beer() {
    }

    public static Beer from(Country origin) {
        return BeerLoader.load(origin);
    }

    @Override
    public String getId() {
        return getType().name() + "_" + getOrigin().name();
    }

    @Override
    public ItemType getType() {
        return ItemType.BEER;
    }

    public void setOrigin(Country origin) {
        this.origin = origin;
    }

    public Country getOrigin() {
        return origin;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }

    @Override
    public void validate() {
        return;
    }

    public void setPackDiscount(Money packDiscount) {
        this.packDiscount = packDiscount;
    }

    public Money getPackDiscount() {
        return packDiscount;
    }
}
