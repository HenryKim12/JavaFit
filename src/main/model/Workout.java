package model;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.ArrayList;

// Represents a workout as a list of exercises
public class Workout {

    private String title;
    private ArrayList<Exercise> workout;

    // REQUIRES: name is not an empty string
    // EFFECTS: constructs a new workout with a title
    public Workout(String title) {
        this.title = title;
        this.workout = new ArrayList<Exercise>();
    }

    // MODIFIES: this
    // EFFECTS: if the given exercise is not in the workout,
    //          then add the exercise; otherwise, add the
    //          sets and reps to the same exercise in the list
    public void addExercise(Exercise e) {
        if (!workout.contains(e)) {
            workout.add(e);
        } else {
            for (Exercise exercise : workout) {
                if (exercise.getName() == e.getName()) {
                    exercise.addSet(e.getSets());
                    exercise.addRep(e.getReps());
                }
            }
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

    public Exercise getExercise(ArrayList<Exercise> workout, int index) {
        this.workout = workout;
        return workout.get(index);
    }
}
