package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class JavaFit implements ActionListener {

    public JavaFit() {
        //Create and set up the window.
        JFrame frame = new JFrame("JavaFit");
        frame.setVisible(true);
        frame.setSize(new Dimension(1000, 1000));
        frame.setLayout(new BorderLayout());

        // Displays the window
        frame.pack();
        frame.setVisible(true);

        // graph panel area
//        JPanel graphPanel = new JPanel();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton workoutsButton = new JButton("Workouts");
        workoutsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton fitnessGoalsButton = new JButton("Fitness Goals");

        JButton mealsButton = new JButton("Meals");

        JButton loadButton = new JButton("Load Data");

        JButton saveButton = new JButton("Save");

        buttonPanel.add(workoutsButton);
        buttonPanel.add(fitnessGoalsButton);
        buttonPanel.add(mealsButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JavaFit();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
