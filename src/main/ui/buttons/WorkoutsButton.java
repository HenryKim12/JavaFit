package ui.buttons;

import ui.JavaFit;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class WorkoutsButton {

    private JFrame frame;

    public WorkoutsButton() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(420, 420));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        setUpFrame();
    }

    public void setUpFrame() {
        JPanel workoutTypes = new JPanel();
        workoutTypes.setLayout(new BoxLayout(workoutTypes, BoxLayout.Y_AXIS));
        JButton push = new JButton("Push Workout");
        JButton pull = new JButton("Pull Workout");
        JButton legs = new JButton("Legs Workout");
        workoutTypes.add(push);
        workoutTypes.add(pull);
        workoutTypes.add(legs);
        frame.add(workoutTypes, BorderLayout.WEST);
    }
}
