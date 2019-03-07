import java.util.ArrayList;
import java.util.List;

public class ActivityBuilder {
    private String name;
    private List<Condition<Student>> conditions = new ArrayList<>();
    private Knowledge knowledge;

    public ActivityBuilder(String name, Condition<Student> condition) {
        this.name = name;
        knowledge = Knowledge.empty();
        conditions.add(condition);
    }

    public ActivityBuilder addConditions(Condition<Student> condition) {
        conditions.add(condition);
        return this;
    }

    public ActivityBuilder addPractice(int practice) {
        knowledge.addPractical(practice);
        return this;
    }

    public ActivityBuilder addTheoretical(int theoretical) {
        knowledge.addTheoretical(theoretical);
        return this;
    }

    public Activity build() {
        Activity activity = new Activity(name, knowledge.getPractical(), knowledge.getTheoretical(), conditions.get(0));
        for (int i = 1; i < conditions.size(); i++) {
            activity.addCondition(conditions.get(i));
        }
        return activity;
    }
}
