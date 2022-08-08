package ui.buttons;

import model.Workout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the frame that opens when pressing workouts button on main frame
public class WorkoutsGUI {

    private JFrame frame;
    private JButton pushWorkoutButton;
    private JButton pullWorkoutButton;
    private JButton legsWorkoutButton;
    private JPanel workoutsButtonsPanel;
    private PushWorkoutGUI pushWorkoutGUI;
    private PullWorkoutGUI pullWorkoutGUI;
    private LegsWorkoutGUI legsWorkoutGUI;
    private Workout push;
    private Workout pull;
    private Workout legs;

    // EFFECTS: constructs the frame
    public WorkoutsGUI(Workout push, Workout pull, Workout legs) {
        this.push = push;
        this.pull = pull;
        this.legs = legs;

        frame = new JFrame("JavaFit");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(100, 125));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        setUpPushWorkoutButton();
        setUpPullWorkoutButton();
        setUpLegsWorkoutButton();

        workoutsButtonsPanel = new JPanel();
        workoutsButtonsPanel.setLayout(new BoxLayout(workoutsButtonsPanel, BoxLayout.Y_AXIS));
        workoutsButtonsPanel.add(pushWorkoutButton);
        workoutsButtonsPanel.add(pullWorkoutButton);
        workoutsButtonsPanel.add(legsWorkoutButton);

        frame.add(workoutsButtonsPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates and implements push workout button
    public void setUpPushWorkoutButton() {
        pushWorkoutButton = new JButton("Push Workout");
        pushWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pushWorkoutGUI = new PushWorkoutGUI(push);
                frame.dispose();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates and implements pull workout button
    public void setUpPullWorkoutButton() {
        pullWorkoutButton = new JButton("Pull Workout");
        pullWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pullWorkoutGUI = new PullWorkoutGUI(pull);
                frame.dispose();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates and implements legs workout button
    public void setUpLegsWorkoutButton() {
        legsWorkoutButton = new JButton("Legs Workout");
        legsWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                legsWorkoutGUI = new LegsWorkoutGUI(legs);
                frame.dispose();
            }
        });
    }

    // EFFECTS: updates push from pushWorkoutGUI
    public void updatePushExercises(Workout push) {
        pushWorkoutGUI.updatePushExercisesGUI(push);
    }

    // EFFECTS: updates pull from pullWorkoutGUI
    public void updatePullExercises(Workout pull) {
        pullWorkoutGUI.updatePullExercisesGUI(pull);
    }

    // EFFECTS: updates legs from legsWorkoutGUI
    public void updateLegsExercises(Workout legs) {
        legsWorkoutGUI.updateLegsExercisesGUI(legs);
    }
}
