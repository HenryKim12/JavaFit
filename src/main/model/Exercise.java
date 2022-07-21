package model;

// Represents an exercise having a name, muscle group, number of sets, and number of reps
public class Exercise {

    private String name;
    private MuscleGroup muscleGroup;
    private int sets;
    private int reps;

    // REQUIRES: name is not an empty string, sets and reps > 0
    // EFFECTS: constructs a new exercise with name, muscle, sets, and reps
    public Exercise(String name, MuscleGroup muscleGroup, int sets, int reps) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.sets = sets;
        this.reps = reps;
    }

    // REQUIRES: numOfSetsAdded > 0
    // MODIFIES: this
    // EFFECTS: adds value of numOfSetsAdded to the exercise's sets
    public void addSet(int numOfSetsAdded) {
        this.sets += numOfSetsAdded;
    }

    // REQUIRES: numOfRepsAdded > 0
    // MODIFIES: this
    // EFFECTS: adds value of numOfRepsAdded to the exercise's reps
    public void addRep(int numOfRepsAdded) {
        this.reps += numOfRepsAdded;
    }

    public String getName() {
        return this.name;
    }

    public MuscleGroup getMuscleGroup() {
        return this.muscleGroup;
    }

    public int getSets() {
        return this.sets;
    }

    public int getReps() {
        return this.reps;
    }
}
