package model;

import java.util.List;

// Represents a workout as a list of exercises
public class Workout {

    private String title;
    private List<Exercise> workout;

    // REQUIRES: name is not an empty string
    // EFFECTS: constructs a new workout with a title
    public Workout(String title) {
        this.title = title;
    }

    // MODIFIES: this
    // EFFECTS: if the given exercise is not in the workout,
    //          then add the exercise; otherwise, add the
    //          sets and reps to the same exercise in the list
    public void addExercise(Exercise e) {
        if (!workout.contains(e)) {
            workout.add(e);
        } else {
            e.addSet(e.getSets()); // do not know how to implement
            e.addRep(e.getReps());
        }
    }

    // MODIFIES: this
    // EFFECTS: if the given exercise is in the workout,
    //          remove it from the workout
    public void removeExercise(Exercise e) {
        if (workout.contains(e)) {
            workout.remove(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: moves an exercise to a certain point in the list
    //          and shifts all following exercises down by one
    public void rearrangeWorkout() {
        // stub - do not know how to implement;
    }

    public String getWorkoutTitle() {
        return this.title;
    }

    public List<Exercise> getWorkout() {
        return this.workout;
    }
}
