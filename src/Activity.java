public class Activity {
    private String name;
    private Knowledge knowledge;

    Activity(String name, int practical, int theoretical) {
        this.name = name;
        knowledge = new Knowledge(practical, theoretical);
    }

    String getName() {
        return name;
    }

    void perform(Student student) {
        student.getKnowledge().add(knowledge);
    }

    int getTheoretical() {
        return knowledge.getTheoretical();
    }

    int getPractical() {
        return knowledge.getPractical();
    }
}
