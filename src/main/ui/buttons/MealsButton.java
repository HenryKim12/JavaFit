package ui.buttons;

import javax.swing.*;
import java.awt.*;

public class MealsButton {

    private JFrame frame;
    private JList allMeals;
    private DefaultListModel listModel;
    private JButton addMeal;

    public MealsButton() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(420, 420));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        setUpMealFrame();
    }

    public void setUpMealFrame() {
        JPanel captionPanel = new JPanel();
        JLabel caption = new JLabel("All Meals: ");
        captionPanel.add(caption, BorderLayout.NORTH);
        frame.add(captionPanel, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listModel = new DefaultListModel();
        allMeals = new JList(listModel);
        listPanel.add(allMeals);
        frame.add(listPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addMeal = new JButton("Add Meal");
        buttonPanel.add(addMeal, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void pressedAddMealButton() {

    }
}
