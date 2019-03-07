import java.time.LocalDate;

public class Student {

    private Knowledge knowledge;
    private LocalDate date;

    Student(Knowledge knowledge, LocalDate dateForStudent) {
        date = dateForStudent;
        this.knowledge = knowledge;
    }

    Student(Knowledge knowledge) {
        this(knowledge, LocalDate.now());
    }

    Student() {
        this(new Knowledge(0, 0));
    }

    Knowledge getKnowledge() {
        return knowledge;
    }

    void perform(KnowledgeType knowledgeType, int value) {
        knowledge.add(knowledgeType, value);
    }

    public void meet(Student otherStudent) {
        if (this.knowledge.get(KnowledgeType.THEORETICAL) < otherStudent.knowledge.get(KnowledgeType.THEORETICAL)) {
            this.knowledge.addTheoretical(
                    (int) ((otherStudent.knowledge.getTheoretical() - this.knowledge.getTheoretical()) * 0.2)
            );
        }
    }

    LocalDate getDate() {
        return date;
    }

    LocalDate nextDay() {
        date = date.plusDays(1);
        return date;
    }

}
