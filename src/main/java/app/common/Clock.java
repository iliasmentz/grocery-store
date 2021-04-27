package app.common;

import java.time.LocalDate;

public class Clock {
    public static LocalDate today() {
        return LocalDate.now();
    }
}
