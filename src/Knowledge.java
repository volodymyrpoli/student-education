public class Knowledge {
    private int practical;
    private int theoretical;

    Knowledge() {
        this(0, 0);
    }

    Knowledge(int practical, int theoretical) {
        this.practical = practical;
        this.theoretical = theoretical;
    }

    void addPractical(int value) {
        practical += value;
    }

    void addTheoretical(int value) {
        theoretical += value;
    }

    void addKnowledge(KnowledgeType type, int value) {
        switch (type) {
            case PRACTICAL: practical += value; break;
            case THEORETICAL: theoretical += value; break;
            default: throw new IllegalArgumentException("First argument must be one of KnowledgeType enum");
        }
    }

    int getPractical() {
        return practical;
    }

    int getTheoretical() {
        return theoretical;
    }

    int getKnowledge(KnowledgeType type) {
        switch (type) {
            case PRACTICAL: return practical;
            case THEORETICAL: return theoretical;
            default: throw new IllegalArgumentException("First argument must be one of KnowledgeType enum");
        }
    }

}
