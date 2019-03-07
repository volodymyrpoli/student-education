import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ActivitiesTest {

    private static Student student_0_0;

    @BeforeEach
    void setUp() {
        student_0_0 = new Student(Knowledge.empty());
    }

    @Test
    void createUniversity__whenStudentHasCorrectDate__addKnowledge() {
        Student studentWithCorrectDate = new Student(Knowledge.empty(), LocalDate.of(2016, Month.SEPTEMBER, 1));
        ActivityWithRegistration university = Activities.createUniversity("Simple University", 2016, 2020, 15, 20);
        university.enroll(studentWithCorrectDate);

        university.perform(studentWithCorrectDate);

        assertThat(studentWithCorrectDate.getKnowledge().getTheoretical(), is(20));
        assertThat(studentWithCorrectDate.getKnowledge().getPractical(), is(15));
    }

    @Test
    void createUniversity__whenStudentHasNotCorrectDate__nothingChange() {
        Student studentWithoutCorrectDate = new Student(Knowledge.empty(), LocalDate.of(2016, Month.AUGUST, 31));
        ActivityWithRegistration university = Activities.createUniversity("Simple University", 2016, 2020, 15, 20);
        university.enroll(studentWithoutCorrectDate);

        university.perform(studentWithoutCorrectDate);

        assertThat(studentWithoutCorrectDate.getKnowledge().getTheoretical(), is(0));
        assertThat(studentWithoutCorrectDate.getKnowledge().getPractical(), is(0));
    }

    @Test
    void createUniversitySpecialDate__whenStudentHasCorrectDate__addKnowledge() {
        Student studentWithoutCorrectDate = new Student(Knowledge.empty(), LocalDate.of(2016, Month.SEPTEMBER, 1));
        ActivityWithRegistration university = Activities.createUniversity(
                "Simple University",
                LocalDate.of(2016, Month.SEPTEMBER, 1),
                LocalDate.of(2017, Month.JULY, 31),
                15,
                20
        );
        university.enroll(studentWithoutCorrectDate);

        university.perform(studentWithoutCorrectDate);

        assertThat(studentWithoutCorrectDate.getKnowledge().getTheoretical(), is(20));
        assertThat(studentWithoutCorrectDate.getKnowledge().getPractical(), is(15));
    }

    @Test
    void createMeetUpPeriodical__whenStudentHasCorrectDate__addKnowledge() {
        Student studentWithoutCorrectDate = new Student(Knowledge.empty(), LocalDate.of(2019, Month.MARCH, 7));
        Activity meetUp = Activities.createMeetUp("Interlink MEET:UP", 2, DayOfWeek.THURSDAY.getValue(), 20, 25);

        meetUp.perform(studentWithoutCorrectDate);

        assertThat(studentWithoutCorrectDate.getKnowledge().getPractical(), is(20));
        assertThat(studentWithoutCorrectDate.getKnowledge().getTheoretical(), is(25));
    }

    @Test
    void createMeetUpSpecialDate__whenStudentHasCorrectDate__addKnowledge() {
        Student studentWithoutCorrectDate = new Student(Knowledge.empty(), LocalDate.of(2019, Month.MARCH, 7));
        Activity meetUp = Activities.createMeetUp("Interlink MEET:UP", LocalDate.of(2019, Month.MARCH, 7), 20, 25);

        meetUp.perform(studentWithoutCorrectDate);

        assertThat(studentWithoutCorrectDate.getKnowledge().getPractical(), is(20));
        assertThat(studentWithoutCorrectDate.getKnowledge().getTheoretical(), is(25));
    }

    @Test
    void createInternship__whenStudentHasCorrectDate__addKnowledge() {
        Student studentWithoutCorrectDate = new Student(Knowledge.empty(), LocalDate.of(2019, Month.MARCH, 7));
        ActivityWithRegistration inCAMP = Activities.createInternship(
                "inCAMP",
                LocalDate.of(2019, Month.MARCH, 4),
                LocalDate.of(2019, Month.MAY, 30),
                2000,
                2500);

        inCAMP.enroll(studentWithoutCorrectDate);
        inCAMP.perform(studentWithoutCorrectDate);

        assertThat(studentWithoutCorrectDate.getKnowledge().getPractical(), is(2000));
        assertThat(studentWithoutCorrectDate.getKnowledge().getTheoretical(), is(2500));
    }


    @Test
    void createSelfEducation__addKnowledge() {
        Student studentWithoutCorrectDate = new Student(Knowledge.empty());
        Activity smartestEveryDay = Activities.createSelfEducation(20, 30);

        smartestEveryDay.perform(studentWithoutCorrectDate);

        assertThat(studentWithoutCorrectDate.getKnowledge().getPractical(), is(20));
        assertThat(studentWithoutCorrectDate.getKnowledge().getTheoretical(), is(30));
    }

}
