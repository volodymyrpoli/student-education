
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FirstGoodTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void zero() {
        MatcherAssert.assertThat(0, Matchers.is(0));
    }
}
