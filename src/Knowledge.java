import java.util.Objects;

public class Knowledge {

    public static final double EDUCABILITY_MAX_VALUE = 1.;
    public static final double EDUCABILITY_MIN_VALUE = 0.01;

    private double practical;
    private double theoretical;
    public final double educability;

    Knowledge(int practical, int theoretical, final double educability) {
        this.practical = practical;
        this.theoretical = theoretical;
        if (educability >= EDUCABILITY_MIN_VALUE && educability <= EDUCABILITY_MAX_VALUE) {
            this.educability = educability;
        } else {
            throw new IllegalArgumentException("Educability must be in range of 0.01 to 1.0");
        }
    }

    Knowledge(int practical, int theoretical) {
        this(practical, theoretical, 1.0);
    }

    void addPractical(int value) {
        practical += value;
    }

    void addTheoretical(int value) {
        theoretical += value * educability;
    }

    void add(KnowledgeType type, int value) {
        switch (type) {
            case PRACTICAL: {
                this.addPractical(value);
            }
            break;
            case THEORETICAL: {
                this.addTheoretical(value);
            }
            break;
            default:
                throw new IllegalArgumentException("First argument must be one of KnowledgeType enum");
        }
    }

    void add(Knowledge knowledge) {
        this.add(KnowledgeType.THEORETICAL, knowledge.getTheoretical());
        this.add(KnowledgeType.PRACTICAL, knowledge.getPractical());
    }

    int getPractical() {
        return (int) practical;
    }

    int getTheoretical() {
        return (int) theoretical;
    }

    int get(KnowledgeType type) {
        switch (type) {
            case PRACTICAL:
                return getPractical();
            case THEORETICAL:
                return getTheoretical();
            default:
                throw new IllegalArgumentException("First argument must be one of KnowledgeType enum");
        }
    }

    public static Knowledge empty() {
        return new Knowledge(0, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knowledge knowledge = (Knowledge) o;
        return Double.compare(knowledge.practical, practical) == 0 &&
                Double.compare(knowledge.theoretical, theoretical) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(practical, theoretical);
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "practical=" + practical +
                ", theoretical=" + theoretical +
                ", educability=" + educability +
                '}';
    }
}
