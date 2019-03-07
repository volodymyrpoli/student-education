package main.activity;

import main.condition.Condition;
import main.Knowledge;
import main.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity {
    private String name;
    private Knowledge knowledge;
    private List<Condition<Student>> conditions;

    @SafeVarargs
    public Activity(String name, int practical, int theoretical, Condition<Student> one, Condition<Student>... conditions) {
        this.name = name;
        knowledge = new Knowledge(practical, theoretical);
        this.conditions = new ArrayList<>();
        this.conditions.add(one);
        this.conditions.addAll(Arrays.asList(conditions));
    }

    public String getName() {
        return name;
    }

    public void perform(Student student) {
        if (conditions.stream().allMatch(condition -> condition.test(student))) {
            student.getKnowledge().add(knowledge);
        }
    }

    public int getTheoretical() {
        return knowledge.getTheoretical();
    }

    public int getPractical() {
        return knowledge.getPractical();
    }

    public void addCondition(Condition<Student> condition) {
        conditions.add(condition);
    }

}
