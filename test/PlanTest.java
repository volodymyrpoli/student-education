import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PlanTest {

    private Plan emptyPlan;
    private Activity meetup_Interlink_10_15;

    @BeforeEach
    void setUp() {
        emptyPlan = new Plan();
        meetup_Interlink_10_15 = new Activity("Interlink MEET:UP", 10, 20, Conditions.everyDay());
    }

    @Test
    void create() {
        assertThat(emptyPlan, notNullValue());
    }

    @Test
    void perform__whenPlanIsEmpty__nothingChange() {
        Student student = new Student();

        emptyPlan.perform(student);

        assertThat(student.getKnowledge().getPractical(), is(0));
        assertThat(student.getKnowledge().getTheoretical(), is(0));
    }

    @Test
    void addActivity__notEmptyActivities() {
        emptyPlan.addActivity(meetup_Interlink_10_15);

        assertThat(emptyPlan.isActivitiesEmpty(), is(false));
    }

    @Test
    void isActivitiesEmpty__emptyActivities() {
        assertThat(emptyPlan.isActivitiesEmpty(), is(true));
    }

    @Test
    void getActivities__whenNoActivitiesAdded__getEmptyList() {
        assertThat(emptyPlan.getActivities(), is(Collections.EMPTY_LIST));
    }

    @Test
    void perform__whenPlanHasOneActivity__addKnowledgeToStudent() {
        Student student = new Student();
        emptyPlan.addActivity(meetup_Interlink_10_15);

        emptyPlan.perform(student);

        assertThat(student.getKnowledge().getTheoretical(), is(20));
        assertThat(student.getKnowledge().getPractical(), is(10));
    }

}
