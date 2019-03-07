package main;

import main.enums.KnowledgeType;

import java.util.Objects;

public class Knowledge {

    public static final double EDUCABILITY_MAX_VALUE = 1.;
    public static final double EDUCABILITY_MIN_VALUE = 0.01;

    private double practical;
    private double theoretical;
    public final double educability;

    public Knowledge(int practical, int theoretical, final double educability) {
        this.practical = practical;
        this.theoretical = theoretical;
        if (educability >= EDUCABILITY_MIN_VALUE && educability <= EDUCABILITY_MAX_VALUE) {
            this.educability = educability;
        } else {
            throw new IllegalArgumentException("Educability must be in range of 0.01 to 1.0");
        }
    }

    public Knowledge(int practical, int theoretical) {
        this(practical, theoretical, 1.0);
    }

    public void addPractical(int value) {
        practical += value;
    }

    public void addTheoretical(int value) {
        theoretical += value * educability;
    }

    public void add(KnowledgeType type, int value) {
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
                throw new IllegalArgumentException("First argument must be one of main.enums.KnowledgeType enum");
        }
    }

    public void add(Knowledge knowledge) {
        this.add(KnowledgeType.THEORETICAL, knowledge.getTheoretical());
        this.add(KnowledgeType.PRACTICAL, knowledge.getPractical());
    }

    public int getPractical() {
        return (int) practical;
    }

    public int getTheoretical() {
        return (int) theoretical;
    }

    public int get(KnowledgeType type) {
        switch (type) {
            case PRACTICAL:
                return getPractical();
            case THEORETICAL:
                return getTheoretical();
            default:
                throw new IllegalArgumentException("First argument must be one of main.enums.KnowledgeType enum");
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
        return "main.Knowledge{" +
                "practical=" + practical +
                ", theoretical=" + theoretical +
                ", educability=" + educability +
                '}';
    }
}
