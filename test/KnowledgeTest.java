import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class KnowledgeTest {

    private Knowledge knowledge_10_20;
    private Knowledge knowledge_0_0;

    @BeforeEach
    void setUp() {
        knowledge_10_20 = new Knowledge(10, 20);
        knowledge_0_0 = new Knowledge();
    }

    @Test
    void knowledge__whenCreateWithParameters__getKnowledge() {
        assertThat(knowledge_10_20.getPractical(), is(10));
        assertThat(knowledge_10_20.getTheoretical(), is(20));
    }

    @Test
    void knowledge__whenCreateWithoutParameters__getZeroKnowledge() {
        assertThat(knowledge_0_0.getPractical(), is(0));
        assertThat(knowledge_0_0.getTheoretical(), is(0));
    }

    @Test
    void knowledge__whenGetKnowledge__getKnowledge() {
        assertThat(knowledge_10_20.get(KnowledgeType.PRACTICAL), is(10));
        assertThat(knowledge_10_20.get(KnowledgeType.THEORETICAL), is(20));
    }

    @Test
    void knowledge__whenAddTheoretical__changeTheoretical() {
        assertThat(knowledge_0_0.getTheoretical(), is(0));

        knowledge_0_0.addTheoretical(15);

        assertThat(knowledge_0_0.getTheoretical(), is(15));
    }

    @Test
    void knowledge__whenAddPractical__changePractical() {
        assertThat(knowledge_0_0.getPractical(), is(0));

        knowledge_0_0.addPractical(10);

        assertThat(knowledge_0_0.getPractical(), is(10));
    }

    @Test
    void knowledge__whenAddKnowledge__changeKnowledge() {
        assertThat(knowledge_0_0.getPractical(), is(0));
        assertThat(knowledge_0_0.getTheoretical(), is(0));

        knowledge_0_0.add(KnowledgeType.PRACTICAL, 15);

        assertThat(knowledge_0_0.getPractical(), is(15));

        knowledge_0_0.add(KnowledgeType.THEORETICAL, 20);

        assertThat(knowledge_0_0.getTheoretical(), is(20));
    }


}
