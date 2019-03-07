import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {

    private Knowledge knowledge;
    private LocalDate date;
    private List<Instrument> instruments = new ArrayList<>();

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

    Student addInstrument(Instrument instrument) {
        instruments.add(instrument);
        return this;
    }

    void removeInstrument(Instrument instrument) {
        instruments.remove(instrument);
    }

    boolean hasInstrument(Instrument instrument) {
        return instruments.contains(instrument);
    }

    int countInstruments() {
        return instruments.size();
    }

}
