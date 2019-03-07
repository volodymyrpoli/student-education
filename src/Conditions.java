import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Conditions {
    private Conditions() {}

    public static Condition<Student> inPeriod(LocalDate from, LocalDate to) {
        return student -> student.getDate().isAfter(from) && student.getDate().isBefore(to);
    }

    public static Condition<Student> inStudyYearsPeriod(int from, int to) {
        return student -> student.getDate().isAfter(LocalDate.of(from, Month.SEPTEMBER, 1))
                && student.getDate().isBefore(LocalDate.of(to, Month.JULY, 1));
    }

    public static Condition<Student> workDay() {
        return student -> student.getDate().getDayOfWeek() != DayOfWeek.SATURDAY
                && student.getDate().getDayOfWeek() != DayOfWeek.SUNDAY;
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