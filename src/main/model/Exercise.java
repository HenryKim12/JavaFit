package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// Represents an exercise having a name, muscle group, number of sets, and number of reps
public class Exercise implements Writable {

    private String name;
    private String muscleGroup;
    private int sets;
    private int reps;

    // REQUIRES: name is not an empty string, sets and reps > 0
    // EFFECTS: constructs a new exercise with name, muscle, sets, and reps
    public Exercise(String name, String muscleGroup, int sets, int reps) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.sets = sets;
        this.reps = reps;
    }

    // REQUIRES: numOfSetsAdded > 0
    // MODIFIES: this
    // EFFECTS: adds value of numOfSetsAdded to the exercise's sets
    public int addSet(int numOfSetsAdded) {
        this.sets += numOfSetsAdded;
        return this.sets;
    }

    // REQUIRES: numOfRepsAdded > 0
    // MODIFIES: this
    // EFFECTS: adds value of numOfRepsAdded to the exercise's reps
    public int addRep(int numOfRepsAdded) {
        this.reps += numOfRepsAdded;
        return this.reps;
    }

    public String getName() {
        return this.name;
    }

    public String getMuscleGroup() {
        return this.muscleGroup;
    }

    public int getSets() {
        return this.sets;
    }

    public int getReps() {
        return this.reps;
    }

    public void setSets(int newSetNum) {
        this.sets = newSetNum;
    }

    public void setReps(int newRepNum) {
        this.reps = newRepNum;
    }

    @Override
    public boolean equals(Object otherObject) {
        boolean isEqual = false;
        if (otherObject instanceof Exercise) {
            Exercise otherExercise = (Exercise) otherObject;
            isEqual = this.name.equals(otherExercise.getName());
        }
        return isEqual;
    }

    public JSONObject toJsonPush() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("muscleGroup", muscleGroup);
        json.put("sets", sets);
        json.put("reps", reps);
        return json;
    }

    public JSONObject toJsonPull() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("muscleGroup", muscleGroup);
        json.put("sets", sets);
        json.put("reps", reps);
        return json;
    }

    public JSONObject toJsonLegs() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("muscleGroup", muscleGroup);
        json.put("sets", sets);
        json.put("reps", reps);
        return json;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
