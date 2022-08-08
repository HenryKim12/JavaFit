package ui.listmodels;

import model.Exercise;
import model.Workout;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

// Represents the list model for pull workout
public class PullWorkoutListModel implements ListModel {

    Workout pull;
    private ArrayList listeners = new ArrayList();

    // EFFECTS: sets the list model as pull
    public PullWorkoutListModel(Workout pull) {
        this.pull = pull;
    }

    @Override
    public int getSize() {
        return pull.getWorkout().size();
    }

    @Override
    public Object getElementAt(int index) {
        return pull.getWorkout().get(index);
    }

    // MODIFIES: this
    // EFFECTS: adds the given exercise to list
    public void addExercise(Exercise e) {
        pull.addExercise(e);
        notifyListeners();
    }

    // MODIFIES: this
    // EFFECTS: removes the exercise at the given index
    public void removeExerciseAt(int index) {
        Exercise toRemove = pull.getWorkout().get(index);
        pull.removeExercise(toRemove);
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
        ListDataEvent le = new ListDataEvent(pull, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
        for (int i = 0; i < listeners.size(); i++) {
            ((ListDataListener) listeners.get(i)).contentsChanged(le);
        }
    }

    // EFFECTS: updates pull
    public void updatePullExercises(Workout pull) {
        this.pull = pull;
        notifyListeners();
    }
}
