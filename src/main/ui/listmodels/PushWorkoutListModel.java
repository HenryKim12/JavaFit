package ui.listmodels;

import model.Exercise;
import model.Workout;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

// Represents the list model for push workout
public class PushWorkoutListModel implements ListModel {

    Workout push;
    private ArrayList listeners = new ArrayList();

    // EFFECTS: sets list model as push
    public PushWorkoutListModel(Workout push) {
        this.push = push;
    }

    @Override
    public int getSize() {
        return push.getWorkout().size();
    }

    @Override
    public Object getElementAt(int index) {
        return push.getWorkout().get(index);
    }

    // MODIFIES: this
    // EFFECTS: adds the given exercise to the list
    public void addExercise(String name, String muscle, String sets, String reps) {
        push.addExercise(new Exercise(name, muscle, Integer.parseInt(sets), Integer.parseInt(reps)));
//        push.addExercise(e);
        notifyListeners();
    }

    // MODIFIES: this
    // EFFECTS: removes the exercise at the given index
    public void removeExerciseAt(int index) {
        Exercise toRemove = push.getWorkout().get(index);
        push.removeExercise(toRemove);
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
        ListDataEvent le = new ListDataEvent(push, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
        for (int i = 0; i < listeners.size(); i++) {
            ((ListDataListener) listeners.get(i)).contentsChanged(le);
        }
    }

    // EFFECTS: updates push
    public void updatePushExercises(Workout push) {
        this.push = push;
        notifyListeners();
    }
}
