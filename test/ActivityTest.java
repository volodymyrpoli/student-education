import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

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
        meetup_Interlink_20_10 = new Activity("Interlink MEET:UP", 10, 20, Conditions.everyDay());
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

    @Test
    void activityCondition__whenActiveOnlyWorkDay__addKnowledge() {
        Activity activity = new Activity("", 15, 20, Conditions.workDay());
        Student student = new Student(Knowledge.empty(), LocalDate.of(2019, Month.MARCH, 7));

        activity.perform(student);

        assertThat(student.getKnowledge().getTheoretical(), is(20));
        assertThat(student.getKnowledge().getPractical(), is(15));
    }

    @Test
    void activityCondition__whenActiveOnlyWorkDayAndStudentHasSaturday__nothingChange() {
        Activity activity = new Activity("", 15, 20, Conditions.workDay());
        Student student = new Student(Knowledge.empty(), LocalDate.of(2019, Month.MARCH, 9));

        activity.perform(student);

        assertThat(student.getKnowledge().getTheoretical(), is(0));
        assertThat(student.getKnowledge().getPractical(), is(0));
    }

    @Test
    void activityCondition__whenActiveOnlyWorkDayAndStudentHasSunday__nothingChange() {
        Activity activity = new Activity("", 15, 20, Conditions.workDay());
        Student student = new Student(Knowledge.empty(), LocalDate.of(2019, Month.MARCH, 10));

        activity.perform(student);

        assertThat(student.getKnowledge().getTheoretical(), is(0));
        assertThat(student.getKnowledge().getPractical(), is(0));
    }

    @Test
    void oneDayOfMonth__whenStudentHasCorrectDate__addKnowledge() {
        Activity activity = new Activity("", 15, 20, Conditions.oneDayOnMonth(3, DayOfWeek.THURSDAY.getValue()));
        Student student = new Student(Knowledge.empty(), LocalDate.of(2019, Month.MARCH, 14));

        activity.perform(student);

        assertThat(student.getKnowledge().getTheoretical(), is(20));
        assertThat(student.getKnowledge().getPractical(), is(15));
    }

    @Test
    void oneDayOfMonth__whenStudentHasNotCorrectDate__nothingChange() {
        Activity activity = new Activity("", 15, 20, Conditions.oneDayOnMonth(3, DayOfWeek.THURSDAY.getValue()));
        Student student = new Student(Knowledge.empty(), LocalDate.of(2019, Month.MARCH, 15));

        activity.perform(student);

        assertThat(student.getKnowledge().getTheoretical(), is(0));
        assertThat(student.getKnowledge().getPractical(), is(0));
    }

    @Test
    void inStudyYearsPeriod__whenStudentHasCorrectDate__addKnowledge() {
        Activity activity = new Activity("University", 1, 5, Conditions.inStudyYearsPeriod(2016, 2020));
        Student student = new Student(Knowledge.empty(), LocalDate.of(2019, Month.MARCH, 15));

        activity.perform(student);

        assertThat(student.getKnowledge().getTheoretical(), is(5));
        assertThat(student.getKnowledge().getPractical(), is(1));
    }

    @Test
    void inStudyYearsPeriod__whenStudentHasNotCorrectDate__nothingChange() {
        Activity activity = new Activity("University", 1, 5, Conditions.inStudyYearsPeriod(2016, 2020));
        Student student = new Student(Knowledge.empty(), LocalDate.of(2021, Month.MARCH, 15));

        activity.perform(student);

        assertThat(student.getKnowledge().getTheoretical(), is(0));
        assertThat(student.getKnowledge().getPractical(), is(0));
    }

    @Test
    void hasInstrument__whenStudentHasNotInstrument__nothingChange() {
        Activity oop_workshop = new Activity("OOP workshop", 15, 10, Conditions.hasInstrument(Instrument.LAPTOP));
        Student student = new Student(Knowledge.empty()).addInstrument(Instrument.PHONE);

        oop_workshop.perform(student);

        assertThat(student.getKnowledge(), is(Knowledge.empty()));
    }

    @Test
    void hasInstrument__whenStudentHasInstrument__addKnowledge() {
        Activity oop_workshop = new Activity("OOP workshop", 15, 10, Conditions.hasInstrument(Instrument.LAPTOP));
        Student student = new Student(Knowledge.empty())
                .addInstrument(Instrument.PHONE)
                .addInstrument(Instrument.LAPTOP);

        oop_workshop.perform(student);

        assertThat(student.getKnowledge(), is(new Knowledge(15, 10)));
    }

}
