import main.activity.ActivityWithMeeting;
import main.condition.Conditions;
import main.Knowledge;
import main.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ActivityWithMeetingTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void perform__whenHasTwoOtherStudent__addKnowledge() {
        ActivityWithMeeting activity = new ActivityWithMeeting("Party", 0, 0, Conditions.everyDay());
        activity
                .addStudent(new Student(new Knowledge(10, 10)))
                .addStudent(new Student(new Knowledge(20, 20)));
        Student student = new Student(Knowledge.empty());

        activity.perform(student);

        assertThat(student.getKnowledge(), is(new Knowledge(0, 5)));
    }

}
