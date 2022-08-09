package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of all fitness goals
public class ListOfFitnessGoals implements Writable {

    private ArrayList<FitnessGoal> allFitnessGoals;

    // EFFECTS: constructs a list of fitness goals
    public ListOfFitnessGoals() {
        this.allFitnessGoals = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if the given fitness goal is not in the list,
    //          then add the fitness goal and produce true;
    //          otherwise, produce false
    public boolean addFitnessGoal(FitnessGoal fg) {
        if (!allFitnessGoals.contains(fg)) {
            allFitnessGoals.add(fg);
            EventLog.getInstance().logEvent(new Event("- " + fg.getGoal()
                    + " added to list of fitness goals!"));
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
            EventLog.getInstance().logEvent(new Event("- " + fg.getGoal()
                    + " removed from list of fitness goals!"));
            return true;
        }
        return false;
    }

    public String getFitnessGoal(int index) {
        return this.allFitnessGoals.get(index).getGoal();
    }

    public ArrayList<FitnessGoal> getAllFitnessGoals() {
        return this.allFitnessGoals;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("fitness goals", fitnessGoalsToJson());
        return json;
    }

    // EFFECTS: returns fitness goals in the list as a JSON array
    public JSONArray fitnessGoalsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FitnessGoal fg : allFitnessGoals) {
            jsonArray.put(fg.toJson());
        }

        return jsonArray;
    }
}
