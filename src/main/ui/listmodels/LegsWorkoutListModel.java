package ui.listmodels;

import model.Exercise;
import model.Workout;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

// Represents the list model for legs
public class LegsWorkoutListModel implements ListModel {

    Workout legs;
    private ArrayList listeners = new ArrayList();

    // EFFECTS: sets the list model as legs workout
    public LegsWorkoutListModel(Workout legs) {
        this.legs = legs;
    }

    @Override
    public int getSize() {
        return legs.getWorkout().size();
    }

    @Override
    public Object getElementAt(int index) {
        return legs.getWorkout().get(index);
    }

    // MODIFIES: this
    // EFFECTS: adds the given exercise to the workout
    public void addExercise(String name, String muscle, String sets, String reps) {
        legs.addExercise(new Exercise(name, muscle, Integer.parseInt(sets), Integer.parseInt(reps)));
        notifyListeners();
    }

    // MODIFIES: this
    // EFFECTS: removes the exercise at the given index
    public void removeExerciseAt(int index) {
        Exercise toRemove = legs.getWorkout().get(index);
        legs.removeExercise(toRemove);
        notifyListeners();
    }

    // EFFECTS: adds given listener to the listeners
    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    // EFFECTS: removes given listener to the listeners
    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }

    // EFFECTS: notifies the listeners when there has been a change in the list
    void notifyListeners() {
        ListDataEvent le = new ListDataEvent(legs, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
        for (int i = 0; i < listeners.size(); i++) {
            ((ListDataListener) listeners.get(i)).contentsChanged(le);
        }
    }

    // EFFECTS: updates the legs workout
    public void updateLegsExercises(Workout legs) {
        this.legs = legs;
        notifyListeners();
    }
}
