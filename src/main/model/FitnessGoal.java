package model;

// Represents a fitness goal as a sentence
public class FitnessGoal {

    private String fitnessGoal;

    // EFFECTS: constructs a new fitness goal
    public FitnessGoal(String goal) {
        this.fitnessGoal = goal;
    }

    public String getGoal() {
        return this.fitnessGoal;
    }
}
