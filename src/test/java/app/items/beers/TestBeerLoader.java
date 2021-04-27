package app.items.beers;

import app.BaseTest;
import app.common.enums.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class TestBeerLoader extends BaseTest {

    @Test
    public void should_return_a_beer_from_germany() {
        // when
        Beer beer = BeerLoader.load(Country.GERMANY);

        // then
        Assertions.assertEquals(
            Country.GERMANY,
            beer.getOrigin()
        );
        Assertions.assertEquals(
            BigDecimal.valueOf(3.5),
            beer.getPrice().getAmount()
        );
    }

    @Test
    public void should_return_a_beer_from_netherlands() {
        // when
        Beer beer = BeerLoader.load(Country.NETHERLANDS);

        // then
        Assertions.assertEquals(
            Country.NETHERLANDS,
            beer.getOrigin()
        );
        Assertions.assertEquals(
            BigDecimal.valueOf(4.5),
            beer.getPrice().getAmount()
        );
    }

    @Test
    public void should_return_a_beer_from_belgium() {
        // when
        Beer beer = BeerLoader.load(Country.BELGIUM);

        // then
        Assertions.assertEquals(
            Country.BELGIUM,
            beer.getOrigin()
        );
        Assertions.assertEquals(
            BigDecimal.valueOf(5.0),
            beer.getPrice().getAmount()
        );
    }

    @Test
    public void should_return_null_if_fails_to_load() throws FileNotFoundException {
        // given
        MockedStatic<BeerLoader> mocked = Mockito.mockStatic(BeerLoader.class);
        mocked.when(BeerLoader::load).thenThrow(new MockitoException("random error"));

        // when
        Beer beer = BeerLoader.load(Country.NETHERLANDS);

        // then
        Assertions.assertNull(beer);

        // cleanup
        mocked.close();
    }
}
