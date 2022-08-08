package ui;

import model.DailyMeals;
import model.ListOfFitnessGoals;
import model.Workout;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Main class that runs the JavaFit application
public class Main {

    private static final String PUSH_STORE = "./data/pushWorkout.json";
    private static final String PULL_STORE = "./data/pullWorkout.json";
    private static final String LEGS_STORE = "./data/legsWorkout.json";
    private static final String GOALS_STORE = "./data/fitnessGoals.json";
    private static final String MEALS_STORE = "./data/dailyMeals.json";
    private static JsonWriter jsonPushWriter;
    private static JsonReader jsonPushReader;
    private static JsonWriter jsonPullWriter;
    private static JsonReader jsonPullReader;
    private static JsonWriter jsonLegsWriter;
    private static JsonReader jsonLegsReader;
    private static JsonWriter jsonGoalsWriter;
    private static JsonReader jsonGoalsReader;
    private static JsonWriter jsonMealsWriter;
    private static JsonReader jsonMealsReader;
    private static Workout push;
    private static Workout pull;
    private static Workout legs;
    private static ListOfFitnessGoals fitnessGoals;
    private static DailyMeals meals;
    private static JavaFit javaFit;

    // EFFECTS: runs JavaFit
    public static void main(String[] args) {
        push = new Workout("Push Day");
        pull = new Workout("Pull Day");
        legs = new Workout("Leg Day");
        fitnessGoals = new ListOfFitnessGoals();
        meals = new DailyMeals();

        javaFit = new JavaFit(push, pull, legs, fitnessGoals, meals);

        jsonPushWriter = new JsonWriter(PUSH_STORE);
        jsonPushReader = new JsonReader(PUSH_STORE);

        jsonPullWriter = new JsonWriter(PULL_STORE);
        jsonPullReader = new JsonReader(PULL_STORE);

        jsonLegsWriter = new JsonWriter(LEGS_STORE);
        jsonLegsReader = new JsonReader(LEGS_STORE);

        jsonGoalsWriter = new JsonWriter(GOALS_STORE);
        jsonGoalsReader = new JsonReader(GOALS_STORE);

        jsonMealsWriter = new JsonWriter(MEALS_STORE);
        jsonMealsReader = new JsonReader(MEALS_STORE);
    }

    // MODIFIES: this, push
    // EFFECTS: loads push workout from a file and updates push
    public static void loadPushWorkout() {
        try {
            push = jsonPushReader.readPushWorkout();
            javaFit.updatePushExercises(push);
            System.out.println("Loaded " + push.getWorkoutTitle() + " from " + PUSH_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + PUSH_STORE);
        }
    }

    // MODIFIES: this, pull
    // EFFECTS: loads pull workout from file and updates pull
    public static void loadPullWorkout() {
        try {
            pull = jsonPullReader.readPullWorkout();
            javaFit.updatePullExercises(pull);
            System.out.println("Loaded " + pull.getWorkoutTitle() + " from " + PULL_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + PULL_STORE);
        }
    }

    // MODIFIES: this, legs
    // EFFECTS: loads legs workout from file and updates legs
    public static void loadLegsWorkout() {
        try {
            legs = jsonLegsReader.readLegsWorkout();
            javaFit.updateLegsExercises(legs);
            System.out.println("Loaded " + legs.getWorkoutTitle() + " from " + LEGS_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + LEGS_STORE);
        }
    }

    // MODIFIES: this, fitnessGoals
    // EFFECTS: loads fitness goals from file and updates fitnessGoals
    public static void loadFitnessGoals() {
        try {
            fitnessGoals = jsonGoalsReader.readListOfFitnessGoals();
            javaFit.updateFitnessGoals(fitnessGoals);
            System.out.println("Loaded fitness goals from " + GOALS_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + GOALS_STORE);
        }
    }

    // MODIFIES: this, meals
    // EFFECTS: loads meals from file and updates meals
    public static void loadDailyMeals() {
        try {
            meals = jsonMealsReader.readMeals();
            javaFit.updateMeals(meals);
            System.out.println("Loaded meals from " + MEALS_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + MEALS_STORE);
        }
    }

    // EFFECTS: saves push workout to file
    public static void savePushWorkout() {
        try {
            jsonPushWriter.open();
            jsonPushWriter.writePushWorkout(push);
            jsonPushWriter.close();
            System.out.println("Saved " + push.getWorkoutTitle() + " to " + PUSH_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + PUSH_STORE);
        }
    }

    // EFFECTS: saves pull workout to file
    public static void savePullWorkout() {
        try {
            jsonPullWriter.open();
            jsonPullWriter.writePullWorkout(pull);
            jsonPullWriter.close();
            System.out.println("Saved " + pull.getWorkoutTitle() + " to " + PULL_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + PULL_STORE);
        }
    }

    // EFFECTS: saves legs workout to file
    public static void saveLegsWorkout() {
        try {
            jsonLegsWriter.open();
            jsonLegsWriter.writeLegsWorkout(legs);
            jsonLegsWriter.close();
            System.out.println("Saved " + legs.getWorkoutTitle() + " to " + LEGS_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + LEGS_STORE);
        }
    }

    // EFFECTS: saves fitness goals to file
    public static void saveFitnessGoals() {
        try {
            jsonGoalsWriter.open();
            jsonGoalsWriter.writeFitnessGoals(fitnessGoals);
            jsonGoalsWriter.close();
            System.out.println("Saved fitness goals to " + GOALS_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + GOALS_STORE);
        }
    }

    // EFFECTS: saves meals to file
    public static void saveDailyMeals() {
        try {
            jsonMealsWriter.open();
            jsonMealsWriter.writeMeals(meals);
            jsonMealsWriter.close();
            System.out.println("Saved meals to " + MEALS_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + MEALS_STORE);
        }
    }
}
