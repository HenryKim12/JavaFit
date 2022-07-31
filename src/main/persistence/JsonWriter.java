package persistence;

import model.DailyMeals;
import model.ListOfFitnessGoals;
import model.Workout;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of workouts, fitness goals, and meals to file
// based on JsonSerializationDemo; link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    //          be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workout to file
    public void writePushWorkout(Workout workout) {
        JSONObject json = workout.toJsonPush();
        saveToFile(json.toString(TAB));
    }

    public void writePullWorkout(Workout workout) {
        JSONObject json = workout.toJsonPull();
        saveToFile(json.toString(TAB));
    }

    public void writeLegsWorkout(Workout workout) {
        JSONObject json = workout.toJsonLegs();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of fitness goals to file
    public void writeFitnessGoals(ListOfFitnessGoals allGoals) {
        JSONObject json = allGoals.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of eaten meals to file
    public void writeMeals(DailyMeals eatenToday) {
        JSONObject json = eatenToday.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}
