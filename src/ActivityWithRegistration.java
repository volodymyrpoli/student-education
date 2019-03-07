import java.util.HashSet;
import java.util.Set;

public class ActivityWithRegistration extends Activity {
    private Set<Student> students = new HashSet<>();

    @SafeVarargs
    ActivityWithRegistration(String name, int practical, int theoretical, Condition<Student> one, Condition<Student>... conditions) {
        super(name, practical, theoretical, one, conditions);
    }

    void enroll(Student student) {
        students.add(student);
    }

    @Override
    void perform(Student student) {
        if (students.stream().anyMatch(enrolledStudent -> student == enrolledStudent)) {
            super.perform(student);
        }
    }

    public int getStudentCount() {
        return students.size();
    }
}
