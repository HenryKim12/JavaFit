package ui;

import model.FitnessGoal;
import model.ListOfFitnessGoals;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class FitnessGoalsListModel implements ListModel {

    ListOfFitnessGoals fitnessGoals;
    private ArrayList listeners = new ArrayList();

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

    public void addFitnessGoal(String goal) {
        fitnessGoals.addFitnessGoal(new FitnessGoal(goal));
        notifyListeners();
    }

    public void removeFitnessGoalAt(int index) {
        FitnessGoal toRemove = fitnessGoals.getAllFitnessGoals().get(index);
        fitnessGoals.removeFitnessGoal(toRemove);
        notifyListeners();
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }

    void notifyListeners() {
        ListDataEvent le = new ListDataEvent(fitnessGoals, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
        for (int i = 0; i < listeners.size(); i++) {
            ((ListDataListener) listeners.get(i)).contentsChanged(le);
        }
    }

    public void updateFitnessGoals(ListOfFitnessGoals fitnessGoals) {
        this.fitnessGoals = fitnessGoals;
        notifyListeners();
    }
}
