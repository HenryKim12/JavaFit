package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workout, fitness goals, and meals from JSON data stored in file
// based on JsonSerializationDemo; link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workouts, fitness goals, and meals from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public Workout read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkout(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: workout
    // EFFECTS: parses workout from JSON object and returns it
    private Workout parseWorkout(JSONObject jsonObject) {
        String name = jsonObject.getString("title");
        Workout workout = new Workout(name);
        addExercise(workout, jsonObject);
        return workout;
    }

    // MODIFIES: workout
    // EFFECTS: parses exercises from JSON object and adds them to workout
    private void addExercises(Workout workout, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(workout, nextExercise);
        }
    }

    // MODIFIES: workout
    // EFFECTS: parses exercise from JSON object and adds it to workout
    private void addExercise(Workout workout, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String muscleGroup = jsonObject.getString("muscleGroup");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        Exercise exercise = new Exercise(name, muscleGroup, sets, reps);
        workout.addExercise(exercise);
    }

    // MODIFIES: allGoals
    // EFFECTS: parses list of fitness goals from JSON object and returns it
    private ListOfFitnessGoals parseListOfFitnessGoals(JSONObject jsonObject) {
        ListOfFitnessGoals allGoals = new ListOfFitnessGoals();
        addFitnessGoal(allGoals, jsonObject);
        return allGoals;
    }

    // MODIFIES: allGoals
    // EFFECTS: parses fitness goals from JSON object and adds them to list of all goals
    private void addFitnessGoals(ListOfFitnessGoals allGoals, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("fitness goals");
        for (Object json : jsonArray) {
            JSONObject nextFitnessGoal = (JSONObject) json;
            addFitnessGoal(allGoals, nextFitnessGoal);
        }
    }

    // MODIFIES: allGoals
    // EFFECTS: parses fitness goal from JSON object and adds it to the list of all goals
    private void addFitnessGoal(ListOfFitnessGoals allGoals, JSONObject jsonObject) {
        String goal = jsonObject.getString("goal");
        FitnessGoal fitnessGoal = new FitnessGoal(goal);
        allGoals.addFitnessGoal(fitnessGoal);
    }

    // MODIFIES: eatenToday
    // EFFECTS: parses list of meals from JSON object and returns it
    private DailyMeals parseDailyMeals(JSONObject jsonObject) {
        DailyMeals eatenToday = new DailyMeals();
        addMeal(eatenToday, jsonObject);
        return eatenToday;
    }

    // MODIFIES: eatenToday
    // EFFECTS: parses meals from JSON object and adds them to the list of meals
    private void addMeals(DailyMeals eatenToday, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("eaten today");
        for (Object json : jsonArray) {
            JSONObject nextMeal = (JSONObject) json;
            addMeal(eatenToday, nextMeal);
        }
    }

    // MODIFIES: eatenToday
    // EFFECTS: parses meal from JSON object and adds it to the list of meals
    private void addMeal(DailyMeals eatenToday, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int calories = jsonObject.getInt("calories");
        int carbohydrates = jsonObject.getInt("carbohydrates");
        int fats = jsonObject.getInt("fats");
        int protein = jsonObject.getInt("protein");
        Meal meal = new Meal(name, calories, carbohydrates, fats, protein);
        eatenToday.addMeal(meal);
    }
}
