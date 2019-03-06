import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ActivityTest {

    private Activity meetup_Interlink_20_10;
    private Activity shortCourse_15_15;
    private Activity expiredCourse_15_15;
    private Activity expiredCourseTomorrow_14_16;
    private Activity onlyTodayMeetup_20_25;
    private Student zeroKnowledgeStudent;

    @BeforeEach
    void setUp() {
        meetup_Interlink_20_10 = new Activity("Interlink MEET:UP", 10, 20);
        shortCourse_15_15 = new Activity(
                "Short Course", 15, 15,
                Conditions.inPeriod(LocalDate.now().minusDays(1), LocalDate.now().plusDays(10))
        );
        expiredCourse_15_15 = new Activity(
                "Expired date course", 12, 15,
                Conditions.inPeriod(LocalDate.now().minusDays(10), LocalDate.now().minusDays(1))
        );
        expiredCourseTomorrow_14_16 = new Activity(
                "Expired tomorrow course", 14, 16,
                Conditions.dateEqualTo(LocalDate.now().minusDays(1))
        );
        onlyTodayMeetup_20_25 = new Activity(
                "Only today meetup", 20, 25,
                Conditions.dateEqualTo(LocalDate.now())
        );
        zeroKnowledgeStudent = new Student();
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
        meetup_Interlink_20_10.perform(zeroKnowledgeStudent);

        assertThat(zeroKnowledgeStudent.getKnowledge().getTheoretical(), is(20));
        assertThat(zeroKnowledgeStudent.getKnowledge().getPractical(), is(10));
    }

    @Test
    void getPractical() {
        assertThat(meetup_Interlink_20_10.getPractical(), is(10));
    }

    @Test
    void getTheoretical() {
        assertThat(meetup_Interlink_20_10.getTheoretical(), is(20));
    }

    @Test
    void activityCondition__whenPeriodIsNotExpired__addKnowledge() {
        shortCourse_15_15.perform(zeroKnowledgeStudent);

        assertThat(zeroKnowledgeStudent.getKnowledge().getTheoretical(), is(15));
        assertThat(zeroKnowledgeStudent.getKnowledge().getPractical(), is(15));
    }

    @Test
    void activityCondition__whenExpiredPeriodIsExpired__nothingChange() {
        expiredCourse_15_15.perform(zeroKnowledgeStudent);

        assertThat(zeroKnowledgeStudent.getKnowledge().getTheoretical(), is(0));
        assertThat(zeroKnowledgeStudent.getKnowledge().getPractical(), is(0));
    }

    @Test
    void activityCondition__whenExpiredTomorrow__nothingChange() {
        expiredCourseTomorrow_14_16.perform(zeroKnowledgeStudent);

        assertThat(zeroKnowledgeStudent.getKnowledge().getTheoretical(), is(0));
        assertThat(zeroKnowledgeStudent.getKnowledge().getPractical(), is(0));
    }

    @Test
    void activityCondition__whenActiveOnlyToday__addKnowledge() {
        onlyTodayMeetup_20_25.perform(zeroKnowledgeStudent);

        assertThat(zeroKnowledgeStudent.getKnowledge().getPractical(), is(20));
        assertThat(zeroKnowledgeStudent.getKnowledge().getTheoretical(), is(25));
    }

    @Test
    void activityCondition__whenActiveEveryDay__addKnowledge() {
        Activity activity = new Activity("", 15, 20, Conditions.everyDay());
        activity.perform(zeroKnowledgeStudent);

        assertThat(zeroKnowledgeStudent.getKnowledge().getPractical(), is(15));
        assertThat(zeroKnowledgeStudent.getKnowledge().getTheoretical(), is(20));
    }

}
