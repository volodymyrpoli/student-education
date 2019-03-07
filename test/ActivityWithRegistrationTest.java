import main.activity.ActivityWithRegistration;
import main.condition.Conditions;
import main.Knowledge;
import main.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ActivityWithRegistrationTest {

    private ActivityWithRegistration university_10_15;
    private Student student_0_0;

    @BeforeEach
    void setUp() {
        university_10_15 = new ActivityWithRegistration("University", 10, 15, Conditions.workDay());
        student_0_0 = new Student(Knowledge.empty());
    }

    @Test
    void create() {
        assertThat(new ActivityWithRegistration("University", 10, 10, Conditions.workDay()), notNullValue());
    }

    @Test
    void enroll__whenAddOneStudent__returnOneStudent() {
        university_10_15.enroll(student_0_0);

        assertThat(university_10_15.getStudentCount(), is(1));
    }

    @Test
    void enroll__whenNoOneStudentAdded__returnZeroStudent() {
        assertThat(university_10_15.getStudentCount(), is(0));
    }

    @Test
    void enroll__whenAddStudentTwice__returnOneStudent() {
        university_10_15.enroll(student_0_0);
        university_10_15.enroll(student_0_0);

        assertThat(university_10_15.getStudentCount(), is(1));
    }

    @Test
    void perform__whenStudentNotEnroll__nothingChange() {
        university_10_15.perform(student_0_0);

        assertThat(student_0_0.getKnowledge().getPractical(), is(0));
        assertThat(student_0_0.getKnowledge().getTheoretical(), is(0));
    }

    @Test
    void perform__whenStudentEnroll__addKnowledge() {
        university_10_15.enroll(student_0_0);

        university_10_15.perform(student_0_0);

        assertThat(student_0_0.getKnowledge().getPractical(), is(10));
        assertThat(student_0_0.getKnowledge().getTheoretical(), is(15));
    }

}
