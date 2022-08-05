package ui.buttons;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the action listener for the fitness goals add button
public class AddGoalListener extends FitnessGoalsButton implements ActionListener, DocumentListener {

    private JButton button;
    private boolean isEnabled = false;
    private JTextField goal;

    // EFFECTS: constructs the listener around the add button
    public AddGoalListener(JButton button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newGoal = goal.getText();

        if (newGoal.equals("") || listModel.contains(newGoal)) {
            Toolkit.getDefaultToolkit().beep();
            goal.requestFocusInWindow();
            goal.selectAll();
            return;
        }

        listModel.insertElementAt(goal.getText(), listModel.size() - 1);

        goal.requestFocusInWindow();
        goal.setText("");

        listOfGoals.setSelectedIndex(listModel.size() - 1);
        listOfGoals.ensureIndexIsVisible(listModel.size() - 1);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (!isEnabled) {
            button.setEnabled(true);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        doEmptyTextField(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (!doEmptyTextField(e)) {
            if (!isEnabled) {
                button.setEnabled(true);
            }
        }
    }

    private boolean doEmptyTextField(DocumentEvent e) {
        if (e.getDocument().getLength() <= 0) {
            button.setEnabled(false);
            isEnabled = false;
            return true;
        }
        return false;
    }
}
