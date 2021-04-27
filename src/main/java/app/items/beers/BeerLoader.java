package app.items.beers;

import app.common.Logger;
import app.common.enums.Country;
import app.common.exceptions.AppException;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BeerLoader {
    private static final String BEER_CONFIG_FILE = "/beers.yml";

    public static Beer load(Country country) {
        try {
            return load().stream()
                .filter(beer -> beer.getOrigin().equals(country))
                .findFirst()
                .orElseThrow(() -> new AppException("country not found"));
        } catch (Exception e) {
            Logger.error("Failed to load beer: " + e.getMessage());
            return null;
        }
    }

    public static List<Beer> load() throws FileNotFoundException {
        Iterable<Object> beerIterable = loadBeersFromFile();
        return toList(beerIterable);
    }

    private static Iterable<Object> loadBeersFromFile() throws FileNotFoundException {
        Constructor constructor = new Constructor(Beer.class);
        Yaml yaml = new Yaml(constructor);

        URL url = BeerLoader.class.getResource(BEER_CONFIG_FILE);

        return yaml.loadAll(new FileInputStream(url.getFile()));
    }

    private static List<Beer> toList(Iterable<Object> beerIterable) {
        return StreamSupport
            .stream(beerIterable.spliterator(), false)
            .map(beer -> (Beer) beer)
            .collect(Collectors.toList());
    }
}
