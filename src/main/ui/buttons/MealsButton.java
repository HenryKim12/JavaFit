package ui.buttons;

import javax.swing.*;
import java.awt.*;

public class MealsButton {

    private JFrame frame;
    private JList allMeals;
    private DefaultListModel listModel;

    public MealsButton() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(420, 420));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        setUpMealFrame();
    }

    public void setUpMealFrame() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JButton addMeal = new JButton("Add Meal");
        buttonPanel.add(addMeal);
        frame.add(buttonPanel, BorderLayout.WEST);

        JPanel listPanel = new JPanel();
        listModel = new DefaultListModel();
        listModel.addElement("HI");
        allMeals = new JList(listModel);
        frame.add(listPanel,BorderLayout.EAST);
    }
}
