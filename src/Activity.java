import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity {
    private String name;
    private Knowledge knowledge;
    private List<Condition<Student>> conditions;

    @SafeVarargs
    Activity(String name, int practical, int theoretical, Condition<Student> one, Condition<Student> ...conditions) {
        this.name = name;
        knowledge = new Knowledge(practical, theoretical);
        this.conditions = new ArrayList<>();
        this.conditions.add(one);
        this.conditions.addAll(Arrays.asList(conditions));
    }

    String getName() {
        return name;
    }

    void perform(Student student) {
        if (conditions.stream().allMatch(condition -> condition.test(student))) {
            student.getKnowledge().add(knowledge);
        }
    }

    int getTheoretical() {
        return knowledge.getTheoretical();
    }

    int getPractical() {
        return knowledge.getPractical();
    }


}
