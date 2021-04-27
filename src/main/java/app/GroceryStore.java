package app;

import app.common.Clock;
import app.common.Logger;
import app.common.enums.Country;
import app.common.exceptions.ItemExpiredException;
import app.items.Bread;
import app.items.beers.Beer;
import app.order.Order;

public class GroceryStore {

    public static void main(String[] args) {
        Order order = new Order();

        Logger.info("⏳ Adding a fresh 🥖 that should be charged");
        order.addItem(freshBread());
        Logger.info("⏳ Adding another fresh 🥖 that should be charged as well");
        order.addItem(freshBread());

        Logger.info("⏳ Adding two 3-day-old 🥖 that should be charged as one");
        order.addItem(threeDaysOldBread());
        order.addItem(threeDaysOldBread());

        Logger.info("⏳ Adding three 6-day-old 🥖 that should be charged as one");
        order.addItem(sixDaysOldBread());
        order.addItem(sixDaysOldBread());
        order.addItem(sixDaysOldBread());

        try {
            Logger.info("⛔️Adding a too old 🥖 that should throw error");
            order.addItem(tooOldBread());
        } catch (ItemExpiredException e) {
            Logger.info("✅ Failed to add a too old 🥖 as expected");
        }

        Logger.info("⏳ Adding a 🍺🇩🇪 that should have no discount");
        order.addItem(Beer.from(Country.GERMANY));

        Logger.info("⏳ Adding a 🍺🇳🇱 that should have no discount");
        order.addItem(Beer.from(Country.NETHERLANDS));

        Logger.info("⏳ Adding a 🍺🇧🇪 that should have no discount");
        order.addItem(Beer.from(Country.BELGIUM));

        Logger.info("⏳ Adding a six pack of 🍺🇧🇪 that should have an error");
        addSixPack(order, Country.BELGIUM);

        Logger.info("\n================================\n");

        Logger.info("🧾Receipt:");
        Logger.info(order.receipt().print());
    }

    private static void addSixPack(Order order, Country country) {
        for (int beer = 0; beer < 6; beer++) {
            order.addItem(Beer.from(country));
        }
    }

    private static Bread freshBread() {
        return new Bread(Clock.today());
    }

    private static Bread threeDaysOldBread() {
        return new Bread(Clock.today().minusDays(3));
    }

    private static Bread sixDaysOldBread() {
        return new Bread(Clock.today().minusDays(6));
    }

    private static Bread tooOldBread() {
        return new Bread(Clock.today().minusDays(10));
    }
}
