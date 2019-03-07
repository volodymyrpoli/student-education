import java.util.ArrayList;
import java.util.List;

public class Plan {

    private List<Activity> activities = new ArrayList<>();

    void perform(Student student) {
        activities.forEach(activity -> activity.perform(student));
    }

    boolean isActivitiesEmpty() {
        return activities.isEmpty();
    }

    void addActivity(Activity activity) {
        activities.add(activity);
    }

    List<Activity> getActivities() {
        return new ArrayList<>(activities);
    }

}
