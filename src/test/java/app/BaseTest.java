package app;

import app.common.Clock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
public abstract class BaseTest {

    private static final LocalDate TODAY = LocalDate.of(2021, 3, 23);
    protected static MockedStatic<Clock> mocked;

    @BeforeAll
    public static void setup() {
        mockClock();
    }

    @AfterAll
    public static void clear() {
       mocked.close();
    }

    private static void mockClock() {
        mocked = Mockito.mockStatic(Clock.class);
        mocked.when(Clock::today).thenReturn(TODAY);
    }

}
