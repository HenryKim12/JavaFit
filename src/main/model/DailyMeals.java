package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents all the meals eaten in the day
public class DailyMeals implements Writable {

    private ArrayList<Meal> eatenToday;
    private int totalCalories;
    private int totalCarbohydrates;
    private int totalFats;
    private int totalProtein;

    // EFFECTS: constructs an empty list of meals
    public DailyMeals() {
        this.eatenToday = new ArrayList<Meal>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given meal to the list and produces true
    public boolean addMeal(Meal m) {
        this.eatenToday.add(m);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: if the given meal is in the list,
    //          remove it from the list and produce true;
    //          otherwise, produce false
    public boolean removeMeal(Meal m) {
        if (this.eatenToday.contains(m)) {
            this.eatenToday.remove(m);
            return true;
        }
        return false;
    }

    // EFFECTS: retrieves the meal at the given index
    public Meal getMeal(int index) {
        return this.eatenToday.get(index);
    }

    // EFFECTS: produces all the meals in a list
    public ArrayList<Meal> getDailyMeals() {
        return this.eatenToday;
    }

    // EFFECTS: produces the total calories eaten today
    public int getTotalCalories() {
        this.totalCalories = 0;
        for (Meal m : this.eatenToday) {
            this.totalCalories += m.getCalories();
        }
        return this.totalCalories;
    }

    // EFFECTS: produces the total carbs eaten today
    public int getTotalCarbohydrates() {
        this.totalCarbohydrates = 0;
        for (Meal m : this.eatenToday) {
            this.totalCarbohydrates += m.getCarbohydrates();
        }
        return this.totalCarbohydrates;
    }

    // EFFECTS: produces the total fats eaten today
    public int getTotalFats() {
        this.totalFats = 0;
        for (Meal m : eatenToday) {
            this.totalFats += m.getFats();
        }
        return this.totalFats;
    }

    // EFFECTS: produces the total protein eaten today
    public int getTotalProtein() {
        this.totalProtein = 0;
        for (Meal m : eatenToday) {
            this.totalProtein += m.getProtein();
        }
        return this.totalProtein;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("all meals", allMealsToJson());
        return json;
    }

    // EFFECTS: returns all meals eaten as a JSON array
    public JSONArray allMealsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Meal m : eatenToday) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}
