package model;

import java.util.List;

// Represents all the meals eaten in the day
public class AllMeals {

    private List<Meal> eatenToday;
    private int totalCalories;
    private int totalCarbohydrates;
    private int totalFats;
    private int totalProtein;

    // EFFECTS: constructs an empty list of meals
    public AllMeals() {
        this.eatenToday = null;
    }

    // MODIFIES: this
    // EFFECTS: adds the given meal to the list
    public void addMeal(Meal m) {
        eatenToday.add(m);
    }

    // MODIFIES: this
    // EFFECTS: removes the given meal from the list
    public void removeMeal(Meal m) {
        eatenToday.remove(m);
    }

    // EFFECTS: produces the total calories eaten today
    public int getTotalCalories() {
        this.totalCalories = 0;
        for (Meal m : eatenToday) {
            this.totalCalories += m.getCalories();
        }
        return this.totalCalories;
    }

    // EFFECTS: produces the total carbs eaten today
    public int getTotalCarbohydrates() {
        this.totalCarbohydrates = 0;
        for (Meal m : eatenToday) {
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
}
