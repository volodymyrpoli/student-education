import main.Knowledge;
import main.Plan;
import main.Student;
import main.activity.Activities;
import main.activity.Activity;
import main.activity.ActivityWithMeeting;
import main.activity.ActivityWithRegistration;
import main.condition.Conditions;
import main.enums.Instrument;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {
        Student studentPacifist = new Student(Knowledge.empty(), "Pacifist");
        Plan pacifist = createPacifistPlan();
        pacifist.registerToAll(studentPacifist);
        
        Student studentSelfTaught = new Student(Knowledge.empty(), "Self-taught");
        Plan selfTaught = createSelfTaughtPlan();
        selfTaught.registerToAll(studentSelfTaught);

        Student studentTeachMeCompletely = new Student(Knowledge.empty(), "Teach me completely");
        Plan teachMeCompletely = createTeachMeCompletely();
        teachMeCompletely.registerToAll(studentTeachMeCompletely);

        Student studentConscious = new Student(Knowledge.empty(), "Conscious");
        Plan conscious = createStudentConsciousPlan();
        conscious.registerToAll(studentConscious);

        for (LocalDate i = LocalDate.now(); i.isBefore(LocalDate.of(2030, 1, 1)); i = i.plusDays(1)) {
            pacifist.perform(studentPacifist);
            selfTaught.perform(studentSelfTaught);
            teachMeCompletely.perform(studentTeachMeCompletely);
            conscious.perform(studentConscious);
        }

        printFormatted(studentPacifist);
        printFormatted(studentSelfTaught);
        printFormatted(studentTeachMeCompletely);
        printFormatted(studentConscious);
    }

    private static void printFormatted(Student student) {
        String line = "Student [" + completeLineToCharCount(student.getName(), 20) + "] -> \t";
        line += "EXP = " + student.getKnowledge().getTheoretical() + "; \t";
        line += "SKL = " + student.getKnowledge().getPractical() + ";";
        System.out.println(line);
    }

    private static String completeLineToCharCount(String line, int count) {
        StringBuilder builder = new StringBuilder(line);
        for (int i = 0; i < count - line.length(); i++) {
            builder.append(' ');
        }
        return builder.toString();
    }

    private static Plan createPacifistPlan() {
        Plan plan = new Plan();
        ActivityWithRegistration university = new ActivityWithRegistration(
                "University",
                2,
                15,
                Conditions.workDay(),
                Conditions.inStudyYearsPeriod(2019, 2024),
                Conditions.notOnMonth(Month.JUNE),
                Conditions.notOnMonth(Month.JULY),
                Conditions.notOnMonth(Month.AUGUST),
                Conditions.notOnMonth(Month.JANUARY)
        );
        ActivityWithRegistration secondUniversity = new ActivityWithRegistration(
                "University",
                2,
                15,
                Conditions.workDay(),
                Conditions.inStudyYearsPeriod(2025, 2030),
                Conditions.notOnMonth(Month.JUNE),
                Conditions.notOnMonth(Month.JULY),
                Conditions.notOnMonth(Month.AUGUST),
                Conditions.notOnMonth(Month.JANUARY)
        );
        plan.addActivity(university);
        plan.addActivity(secondUniversity);

        return plan;
    }

    private static Plan createSelfTaughtPlan() {
        Plan plan = new Plan();
        Activity meetUp = Activities.createMeetUp(
                "MEET:UP",
                3,
                DayOfWeek.THURSDAY.getValue(),
                0,
                30
        );
        plan.addActivity(meetUp);
        
        Activity workshop = new Activity(
                "Workshop",
                10,
                2,
                Conditions.oneDayOnMonth(1, DayOfWeek.TUESDAY.getValue()),
                Conditions.hasInstrument(Instrument.LAPTOP)
        );
        plan.addActivity(workshop);
        
        Activity selfEducation = Activities.createSelfEducation(1, 5);
        plan.addActivity(selfEducation);

        plan.addActivity(createInCamp());
        
        return plan;
    }

    private static Plan createTeachMeCompletely() {
        Plan plan = new Plan();
        ActivityWithRegistration university = Activities.createUniversity(
                "University",
                2019,
                2024,
                2,
                15
        );
        Activity meetUp = Activities.createMeetUp(
                "MEET:UP",
                3,
                DayOfWeek.THURSDAY.getValue(),
                0,
                30
        );
        Activity waitForEnlightenment = new Activity("Await", -1, -1, Conditions.everyDay());
        Activity goToTheInterview = new Activity(
                "Interview",
                0,
                1,
                Conditions.oneDayOnMonth(3, DayOfWeek.TUESDAY.getValue())
        );
        plan.addActivity(university);
        plan.addActivity(meetUp);
        plan.addActivity(waitForEnlightenment);
        plan.addActivity(goToTheInterview);
        return plan;
    }

    private static Plan createStudentConsciousPlan() {
        Plan plan = new Plan();
        ActivityWithRegistration university = Activities.createUniversity(
                "University",
                2019,
                2022,
                2,
                15
        );
        plan.addActivity(university);
        Activity meetUp = Activities.createMeetUp(
                "MeetUp",
                3,
                DayOfWeek.THURSDAY.getValue(),
                0,
                30
        );
        plan.addActivity(meetUp);
        
        Activity selfEducation = Activities.createSelfEducation(1, 5);
        plan.addActivity(selfEducation);
        
        ActivityWithMeeting meeting = new ActivityWithMeeting(
                "Party",
                0,
                0,
                Conditions.everyDay()
        );
        meeting.addStudent(new Student(new Knowledge(300, 500)));
        meeting.addStudent(new Student(new Knowledge(750, 200)));
        meeting.addStudent(new Student(new Knowledge(100, 2000)));
        plan.addActivity(meeting);
        
        ActivityWithRegistration internship = createInCamp();
        plan.addActivity(internship);

        return plan;
    }

    private static ActivityWithRegistration createInCamp() {
        return Activities.createInternship(
                "inCamp",
                LocalDate.of(2019, Month.MARCH, 4),
                LocalDate.of(2019, Month.MAY, 31),
                150,
                130
        );
    }
}
