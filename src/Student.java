public class Student {
    private Knowledge knowledge;

    Student(Knowledge knowledge) {
        this.knowledge = knowledge;
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
}
