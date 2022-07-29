package persistence;

import model.Workout;
import org.json.JSONObject;

import java.io.*;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public void write(Workout workout) {
        JSONObject json = workout.toJson();
        saveToFile(json.toString(TAB));
    }

    public void close() {
        writer.close();
    }

    public void saveToFile(String json) {
        writer.print(json);
    }
}
