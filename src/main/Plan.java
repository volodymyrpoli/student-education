package main;

import main.activity.Activity;

import java.util.ArrayList;
import java.util.List;

public class Plan {

    private List<Activity> activities = new ArrayList<>();

    public void perform(Student student) {
        activities.forEach(activity -> activity.perform(student));
    }

    public boolean isActivitiesEmpty() {
        return activities.isEmpty();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return new ArrayList<>(activities);
    }

}
