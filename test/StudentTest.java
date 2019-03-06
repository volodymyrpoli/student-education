import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class StudentTest {

    @Test
    void create() {
        new Student();
    }

    @Test
    void create__setKnowledgeAsParameter() {
        new Student(new Knowledge(10, 20));
    }

    @Test
    void getKnowledge__whenCreateWithKnowledge() {
        Student student = new Student(new Knowledge(20, 30));

        assertThat(student.getKnowledge(), notNullValue());
    }

    @Test
    void perform__whenGetTheoreticalKnowledge() {
        Student student = new Student();

        assertThat(student.getKnowledge().getTheoretical(), is(0));

        student.perform(KnowledgeType.THEORETICAL, 15);

        assertThat(student.getKnowledge().getTheoretical(), is(15));
    }

    @Test
    void perform__whenGetPracticalKnowledge() {
        Student student = new Student();

        assertThat(student.getKnowledge().getPractical(), is(0));

        student.perform(KnowledgeType.PRACTICAL, 20);

        assertThat(student.getKnowledge().getPractical(), is(20));
    }

    @Test
    void meet__sharingKnowledge__addKnowledgeFromMoreSmartStudent() {
        Student lessIntelligentStudent = new Student(new Knowledge(10, 10));
        Student moreIntelligentStudent = new Student(new Knowledge(20, 20));

        lessIntelligentStudent.meet(moreIntelligentStudent);

        assertThat(lessIntelligentStudent.getKnowledge().getTheoretical(), is(12));
    }

}
