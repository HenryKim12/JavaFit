package ui.listmodels;

import model.DailyMeals;
import model.Meal;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

// Represents the list model for meals
public class MealsListModel implements ListModel {

    DailyMeals meals;
    private ArrayList listeners = new ArrayList();

    // EFFECTS: sets the list model as meals
    public MealsListModel(DailyMeals meals) {
        this.meals = meals;
    }

    @Override
    public int getSize() {
        return meals.getDailyMeals().size();
    }

    @Override
    public Object getElementAt(int index) {
        return meals.getDailyMeals().get(index);
    }

    // MODIFIES: this, meals
    // EFFECTS: constructs and adds meal to meals
    public void addMeal(String name, String calories, String carbs, String fats, String protein) {
        meals.addMeal(new Meal(name, Integer.parseInt(calories), Integer.parseInt(carbs),
                Integer.parseInt(fats), Integer.parseInt(protein)));
        notifyListeners();
    }

    // MODIFIES: this, meals
    // EFFECTS: removes the meal at the given index
    public void removeMealAt(int index) {
        Meal toRemove = meals.getDailyMeals().get(index);
        meals.removeMeal(toRemove);
        notifyListeners();
    }

    // MODIFIES: listeners
    // EFFECTS: adds given listener to the listeners
    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    // MODIFIES: listeners
    // EFFECTS: removes given listener to the listeners
    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }

    // EFFECTS: notifies the listeners when there has been a change in the list
    void notifyListeners() {
        ListDataEvent le = new ListDataEvent(meals, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
        for (int i = 0; i < listeners.size(); i++) {
            ((ListDataListener) listeners.get(i)).contentsChanged(le);
        }
    }

    // MODIFIES: meals
    // EFFECTS: updates meals
    public void updateMeals(DailyMeals meals) {
        this.meals = meals;
        notifyListeners();
    }
}
