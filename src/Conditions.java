import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Conditions {
    private Conditions() {}

    public static Condition<Student> inPeriod(LocalDate from, LocalDate to) {
        return student -> student.getDate().isAfter(from) && student.getDate().isBefore(to);
    }

    public static Condition<Student> inStudyYearsPeriod(int from, int to) {
        return student -> student.getDate().isAfter(LocalDate.of(from, 9, 1))
                && student.getDate().isBefore(LocalDate.of(to, 6, 1));
    }

    public static Condition<Student> isWorkDay() {
        return student -> student.getDate().getDayOfWeek().getValue() != 6
                && student.getDate().getDayOfWeek().getValue() != 7;
    }

    public static Condition<Student> oneDayOnMonth(int weekOfMonth, int dayOfWeek) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return student -> student.getDate().get(weekFields.weekOfMonth()) == weekOfMonth
                && student.getDate().getDayOfWeek().getValue() == dayOfWeek;
    }

    public static Condition<Student> dateEqualTo(LocalDate localDate) {
        return student -> student.getDate().equals(localDate);
    }

    public static Condition<Student> everyDay() {
        return student -> true;
    }
}
