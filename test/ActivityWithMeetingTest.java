import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ActivityWithMeetingTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void perform__whenHasOneOtherStudent__addKnowledge() {
        ActivityWithMeeting activity = new ActivityWithMeeting("Party", 0, 0, Conditions.everyDay());
        activity
                .addStudent(new Student(new Knowledge(10, 10)))
                .addStudent(new Student(new Knowledge(20, 20)));
        Student student = new Student(Knowledge.empty());

        activity.perform(student);

        assertThat(student.getKnowledge(), is(new Knowledge(0, 5)));
    }
}
