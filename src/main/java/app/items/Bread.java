package app.items;

import app.common.Clock;
import app.common.enums.Money;
import app.common.exceptions.AppException;
import app.common.exceptions.ItemExpiredException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Bread implements Item {
    private static final double BREAD_PRICE_EURO = 1.63;
    private static final int OLDEST_AGE_TO_SELL = 6;
    private final LocalDate producedAt;

    public Bread(LocalDate producedAt) {
        this.producedAt = producedAt;
    }

    @Override
    public String getId() {
        return getType().name() + "_" + producedAt.toString();
    }

    @Override
    public ItemType getType() {
        return ItemType.BREAD;
    }

    @Override
    public Money getPrice() {
        return new Money(BigDecimal.valueOf(BREAD_PRICE_EURO));
    }

    @Override
    public void validate() {
        if (ageInDays() > OLDEST_AGE_TO_SELL) {
            throw new ItemExpiredException();
        }
    }

    public long ageInDays() {
        return DAYS.between(
            producedAt,
            Clock.today()
        );
    }
}
