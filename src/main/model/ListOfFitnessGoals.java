package model;

import java.util.List;

// Represents a list of all fitness goals
public class ListOfFitnessGoals {

    private List<FitnessGoal> allFitnessGoals;

    // EFFECTS: constructs a list of fitness goals
    public ListOfFitnessGoals() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: if the given fitness goal is not in the list,
    //          then add the fitness goal; otherwise, do not add it
    public void addFitnessGoal(FitnessGoal fg) {
        if (!allFitnessGoals.contains(fg)) {
            allFitnessGoals.add(fg);
        }
    }

    // MODIFIES: this
    // EFFECTS: if the given fitness goal is in the list,
    //          remove the fitness goal
    public void removeFitnessGoal(FitnessGoal fg) {
        if (allFitnessGoals.contains(fg)) {
            allFitnessGoals.remove(fg);
        }
    }

    public List<FitnessGoal> getAllFitnessGoals() {
        return this.allFitnessGoals;
    }
}
