package model;

import java.util.ArrayList;

// Represents a list of all fitness goals
public class ListOfFitnessGoals {

    private ArrayList<FitnessGoal> allFitnessGoals;

    // EFFECTS: constructs a list of fitness goals
    public ListOfFitnessGoals() {
        this.allFitnessGoals = new ArrayList<FitnessGoal>();
    }

    // MODIFIES: this
    // EFFECTS: if the given fitness goal is not in the list,
    //          then add the fitness goal and produce true
    //          ;otherwise, produce false
    public boolean addFitnessGoal(FitnessGoal fg) {
        if (!allFitnessGoals.contains(fg)) {
            allFitnessGoals.add(fg);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if the given fitness goal is in the list,
    //          remove it from the list and produce true;
    //          otherwise, produce false
    public boolean removeFitnessGoal(FitnessGoal fg) {
        if (allFitnessGoals.contains(fg)) {
            allFitnessGoals.remove(fg);
            return true;
        }
        return false;
    }

    public ArrayList<FitnessGoal> getAllFitnessGoals() {
        return this.allFitnessGoals;
    }
}
