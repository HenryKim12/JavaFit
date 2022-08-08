package ui.listmodels;

import model.FitnessGoal;
import model.ListOfFitnessGoals;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

// Represents the listModel for fitness goals
public class FitnessGoalsListModel implements ListModel {

    ListOfFitnessGoals fitnessGoals;
    private ArrayList listeners = new ArrayList();

    // EFFECTS: creates list model as list of fitness goals
    public FitnessGoalsListModel(ListOfFitnessGoals fitnessGoals) {
        this.fitnessGoals = fitnessGoals;
    }

    @Override
    public int getSize() {
        return fitnessGoals.getAllFitnessGoals().size();
    }

    @Override
    public Object getElementAt(int index) {
        return fitnessGoals.getAllFitnessGoals().get(index);
    }

    // MODIFIES: this, fitnessGoals
    // EFFECTS: takes the goal and adds it into the list of fitness goals
    public void addFitnessGoal(String goal) {
        fitnessGoals.addFitnessGoal(new FitnessGoal(goal));
        notifyListeners();
    }

    // MODIFIES: this, fitnessGoals
    // EFFECTS: removes the fitness goal at the given index
    public void removeFitnessGoalAt(int index) {
        FitnessGoal toRemove = fitnessGoals.getAllFitnessGoals().get(index);
        fitnessGoals.removeFitnessGoal(toRemove);
        notifyListeners();
    }

    // MODIFIES: listeners
    // EFFECTS: adds given listener to the listeners
    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    // MODIFIES: listeners
    // EFFECTS: removes given listener from the listeners
    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }

    // EFFECTS: notifies the listeners when there has been a change in the list
    void notifyListeners() {
        ListDataEvent le = new ListDataEvent(fitnessGoals, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
        for (int i = 0; i < listeners.size(); i++) {
            ((ListDataListener) listeners.get(i)).contentsChanged(le);
        }
    }

    // MODIFIES: fitnessGoals
    // EFFECTS: updates the fitness goals
    public void updateFitnessGoals(ListOfFitnessGoals fitnessGoals) {
        this.fitnessGoals = fitnessGoals;
        notifyListeners();
    }
}
