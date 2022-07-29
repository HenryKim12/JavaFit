package persistence;


import model.Exercise;
import model.Workout;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workout, fitness goals, and meals from JSON data stored in file
public class JsonReader {

    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public Workout read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkout(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private Workout parseWorkout(JSONObject jsonObject) {
        String name = jsonObject.getString("title");
        Workout workout = new Workout(name);
        addExercise(workout, jsonObject);
        return workout;
    }

    private void addExercises(Workout workout, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(workout, nextExercise);
        }
    }

    private void addExercise(Workout workout, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String muscleGroup = jsonObject.getString("muscleGroup");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        Exercise exercise = new Exercise(name, muscleGroup, sets, reps);
        workout.addExercise(exercise);
    }
}
