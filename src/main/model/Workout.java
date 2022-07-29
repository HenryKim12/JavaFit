package model;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
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
        }
    }

    // MODIFIES: this
    // EFFECTS: if the given exercise is in the workout,
    //          remove it from the workout and produce true;
    //          otherwise, produce false
    public boolean removeExercise(Exercise e) {
        if (workout.contains(e)) {
            workout.remove(e);
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("exercises", exercisesToJson());
        return json;
    }

    // EFFECTS: returns exercises in the workout as a JSON array
    public JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : workout) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}
