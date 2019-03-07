import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ActivityBuilderTest {

    private Activity university_15_250;

    @BeforeEach
    void setUp() {
        university_15_250 = new ActivityBuilder("UniversityForAll", Conditions.inStudyYearsPeriod(2016, 2020))
                .addConditions(Conditions.workDay())
                .addConditions(Conditions.notOnDay(8, Month.MARCH))
                .addConditions(Conditions.notOnDay(1, Month.JANUARY))
                .addPractice(15)
                .addPractice(20)
                .addTheoretical(250)
                .addTheoretical(50)
                .build();
    }

    @Test
    void getTheoretical() {
        assertThat(university_15_250.getTheoretical(), is(300));
    }

    @Test
    void getPractical() {
        assertThat(university_15_250.getPractical(), is(35));
    }

    @Test
    void perform__whenDayInNotWork__nothingChange() {
        Student student = new Student(Knowledge.empty(), LocalDate.of(2019, Month.MARCH, 10));

        university_15_250.perform(student);

        assertThat(student.getKnowledge(), equalTo(Knowledge.empty()));
    }

    @Test
    void perform__whenDayIsWork__addKnowledge() {
        Student student = new Student(Knowledge.empty(), LocalDate.of(2019, Month.MARCH, 4));

        university_15_250.perform(student);

        assertThat(student.getKnowledge().getPractical(), is(35));
        assertThat(student.getKnowledge().getTheoretical(), is(300));
    }

    @Test
    void perform__whenDayNotInStudyPeriod__nothingChange() {
        Student student = new Student(Knowledge.empty(), LocalDate.of(2020, Month.JULY, 31).plusDays(1));

        university_15_250.perform(student);

        assertThat(student.getKnowledge(), equalTo(Knowledge.empty()));
    }

    @Test
    void perform__whenDayInStudyPeriod__addKnowledge() {
        Student student = new Student(Knowledge.empty(), LocalDate.of(2020, Month.JULY, 31));

        university_15_250.perform(student);

        assertThat(student.getKnowledge(), equalTo(new Knowledge(35, 300)));
    }

    @Test
    void getName__whenNameIsUniversity() {
        assertThat(university_15_250.getName(), is("UniversityForAll"));
    }

    @Test
    void perform__whenDayIsVacation__nothingChange() {
        Student student = new Student(Knowledge.empty(), LocalDate.of(2019, Month.MARCH, 8));

        university_15_250.perform(student);

        assertThat(student.getKnowledge(), equalTo(new Knowledge(0, 0)));
    }

}
