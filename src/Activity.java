import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity {
    private String name;
    private Knowledge knowledge;
    private List<Condition<Student>> conditions = new ArrayList<>();

    Activity(String name, int practical, int theoretical) {
        setup(name, practical, theoretical);
    }

    @SafeVarargs
    Activity(String name, int practical, int theoretical, Condition<Student> ...conditions) {
        setup(name, practical, theoretical);
        this.conditions = new ArrayList<>(Arrays.asList(conditions));
    }

    private void setup(String name, int practical, int theoretical) {
        this.name = name;
        knowledge = new Knowledge(practical, theoretical);
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
