package ui.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the action listener for the fitness goals completed button
public class CompletedGoalListener extends FitnessGoalsButton implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        int selected = listOfGoals.getSelectedIndex();
        listModel.remove(selected);

        if (listModel.getSize() == 0) {
            completedButton.setEnabled(false);
        } else {
            if (selected == listModel.getSize()) {
                selected--;
            }
            listOfGoals.setSelectedIndex(selected);
            listOfGoals.ensureIndexIsVisible(selected);
        }
    }
}
