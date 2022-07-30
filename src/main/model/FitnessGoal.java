package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a fitness goal as a sentence
public class FitnessGoal implements Writable {

    private String fitnessGoal;

    // EFFECTS: constructs a new fitness goal
    public FitnessGoal(String goal) {
        this.fitnessGoal = goal;
    }

    public String getGoal() {
        return this.fitnessGoal;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("goal", fitnessGoal);
        return json;
    }
}
