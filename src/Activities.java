import java.time.LocalDate;

public class Activities {
    private Activities() {
    }

    public static ActivityWithRegistration createUniversity(String name, LocalDate start, LocalDate end, int practical, int theoretical) {
        return new ActivityWithRegistration(
                name,
                practical,
                theoretical,
                Conditions.inPeriod(start, end),
                Conditions.workDay()
        );
    }

    public static ActivityWithRegistration createUniversity(String name, int startYear, int endYear, int practical, int theoretical) {
        return new ActivityWithRegistration(
                name,
                practical,
                theoretical,
                Conditions.inStudyYearsPeriod(startYear, endYear),
                Conditions.workDay()
        );
    }

    public static Activity createMeetUp(String name, LocalDate date, int practical, int theoretical) {
        return new Activity(name, practical, theoretical, Conditions.dateEqualTo(date));
    }

    public static Activity createMeetUp(String name, int weekOfMonth, int dayOfWeek, int practical, int theoretical) {
        return new Activity(name, practical, theoretical, Conditions.oneDayOnMonth(weekOfMonth, dayOfWeek));
    }

    public static ActivityWithRegistration createInternship(String name, LocalDate start, LocalDate end, int practical, int theoretical) {
        return new ActivityWithRegistration(name, practical, theoretical, Conditions.inPeriod(start, end), Conditions.workDay());
    }

    public static Activity createSelfEducation(int practical, int theoretical) {
        return new Activity("Self-education", practical, theoretical, Conditions.everyDay());
    }

}
