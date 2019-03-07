import java.util.ArrayList;
import java.util.List;

public class ActivityWithMeeting extends Activity {

    private List<Student> students = new ArrayList<>();

    @SafeVarargs
    ActivityWithMeeting(String name, int practical, int theoretical, Condition<Student> one, Condition<Student>... conditions) {
        super(name, practical, theoretical, one, conditions);
    }

    ActivityWithMeeting addStudent(Student student) {
        students.add(student);
        return this;
    }

    @Override
    void perform(Student student) {
        super.perform(student);
        students.forEach(student::meet);
    }
}
