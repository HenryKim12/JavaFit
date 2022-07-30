package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a meal having a name, calories, carbs, fats, and protein
public class Meal implements Writable {

    private String name;
    private int calories;
    private int carbohydrates;
    private int fats;
    private int protein;

    // EFFECTS: constructs a meal with its name, calories, carbs, fats, and protein
    public Meal(String name, int calories, int carbs, int fats, int protein) {
        this.name = name;
        this.calories = calories;
        this.carbohydrates = carbs;
        this.fats = fats;
        this.protein = protein;
    }

    public String getName() {
        return this.name;
    }

    public int getCalories() {
        return this.calories;
    }

    public int getCarbohydrates() {
        return this.carbohydrates;
    }

    public int getFats() {
        return this.fats;
    }

    public int getProtein() {
        return this.protein;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("calories", calories);
        json.put("carbohydrates", carbohydrates);
        json.put("fats", fats);
        json.put("protein", protein);
        return json;
    }
}
