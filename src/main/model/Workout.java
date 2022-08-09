package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a workout as a list of exercises
public class Workout implements Writable {

    private String title;
    private ArrayList<Exercise> workout;

    // REQUIRES: name is not an empty string
    // EFFECTS: constructs a new workout with a title
    public Workout(String title) {
        this.title = title;
        this.workout = new ArrayList<Exercise>();
    }

    // MODIFIES: this
    // EFFECTS: if the given exercise is in the workout, then add the
    //          sets and reps to the same exercise in the list;
    //          otherwise, add the exercise to the list
    public void addExercise(Exercise e) {
        if (workout.contains(e)) {
            int pos = workout.indexOf(e);
            workout.get(pos).addSet(e.getSets());
            workout.get(pos).addRep(e.getReps());
        } else {
            workout.add(e);
            EventLog.getInstance().logEvent(new Event("- " + e.getName() + " added to workout!"));
        }
    }

    // MODIFIES: this
    // EFFECTS: if the given exercise is in the workout,
    //          remove it from the workout and produce true;
    //          otherwise, produce false
    public boolean removeExercise(Exercise e) {
        if (workout.contains(e)) {
            workout.remove(e);
            EventLog.getInstance().logEvent(new Event("- " + e.getName() + " removed from workout!"));
            return true;
        }
        return false;
    }

    // REQUIRES: the given exercise is already in the workout list
    // MODIFIES: this
    // EFFECTS: moves the given exercise from the initial position
    //          to the new position and shifts all following
    //          exercises down by one
    public void rearrangeWorkout(Exercise e, int newPos) {
        this.workout.remove(e);
        this.workout.add(newPos, e);
    }

    public String getWorkoutTitle() {
        return this.title;
    }

    public ArrayList<Exercise> getWorkout() {
        return this.workout;
    }

    // EFFECTS: retrieves the exercise from the workout at
    //          the given index
    public Exercise getExercise(ArrayList<Exercise> workout, int index) {
        this.workout = workout;
        return workout.get(index);
    }

    public JSONObject toJsonPush() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("exercises", exercisesToJsonPush());
        return json;
    }

    public JSONObject toJsonPull() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("exercises", exercisesToJsonPull());
        return json;
    }

    public JSONObject toJsonLegs() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("exercises", exercisesToJsonLegs());
        return json;
    }

    // EFFECTS: returns exercises in the push workout as a JSON array
    public JSONArray exercisesToJsonPush() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : workout) {
            jsonArray.put(e.toJsonPush());
        }

        return jsonArray;
    }

    // EFFECTS: returns exercises in the pull workout as a JSON array
    public JSONArray exercisesToJsonPull() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : workout) {
            jsonArray.put(e.toJsonPull());
        }

        return jsonArray;
    }

    // EFFECTS: returns exercises in the legs workout as a JSON array
    public JSONArray exercisesToJsonLegs() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : workout) {
            jsonArray.put(e.toJsonLegs());
        }

        return jsonArray;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
