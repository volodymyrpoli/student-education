package main;

import main.enums.Instrument;
import main.enums.KnowledgeType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private Knowledge knowledge;
    private LocalDate date;
    private List<Instrument> instruments = new ArrayList<>();

    public Student(Knowledge knowledge, String name) {
        this(knowledge);
        this.name = name;
    }

    public Student(Knowledge knowledge, LocalDate dateForStudent) {
        date = dateForStudent;
        this.knowledge = knowledge;
    }

    public Student(Knowledge knowledge) {
        this(knowledge, LocalDate.now());
    }

    public Student() {
        this(new Knowledge(0, 0));
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public void perform(KnowledgeType knowledgeType, int value) {
        knowledge.add(knowledgeType, value);
    }

    public void meet(Student otherStudent) {
        if (this.knowledge.get(KnowledgeType.THEORETICAL) < otherStudent.knowledge.get(KnowledgeType.THEORETICAL)) {
            this.knowledge.addTheoretical(
                    (int) ((otherStudent.knowledge.getTheoretical() - this.knowledge.getTheoretical()) * 0.2)
            );
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate nextDay() {
        return date = date.plusDays(1);
    }

    public Student addInstrument(Instrument instrument) {
        instruments.add(instrument);
        return this;
    }

    public void removeInstrument(Instrument instrument) {
        instruments.remove(instrument);
    }

    public boolean hasInstrument(Instrument instrument) {
        return instruments.contains(instrument);
    }

    public int countInstruments() {
        return instruments.size();
    }

    public String getName() {
        return name;
    }
}
