import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ActivityTest {

    private Activity meetup_Interlink_20_10;

    @BeforeEach
    void setUp() {
        meetup_Interlink_20_10 = new Activity("Interlink MEET:UP", 10, 20);
    }

    @Test
    void create() {
        assertThat(meetup_Interlink_20_10, notNullValue());
    }

    @Test
    void getName() {
        assertThat(meetup_Interlink_20_10.getName(), allOf(notNullValue(), is("Interlink MEET:UP")));
    }

    @Test
    void perform() {
        Student student = new Student();
        meetup_Interlink_20_10.perform(student);

        assertThat(student.getKnowledge().getTheoretical(), is(20));
        assertThat(student.getKnowledge().getPractical(), is(10));
    }

    @Test
    void getPractical() {
        assertThat(meetup_Interlink_20_10.getPractical(), is(10));
    }

    @Test
    void getTheoretical() {
        assertThat(meetup_Interlink_20_10.getTheoretical(), is(20));
    }

}
