package ui.buttons;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MealsButton implements ActionListener {

    private JFrame frame;
    private JList allMeals;
    private DefaultListModel listModel;
    private JButton addMeal;
    private AddMealListener addMealListener;
    private JPanel namePanel;
    private JPanel caloriesPanel;
    private JPanel carbsPanel;
    private JPanel fatsPanel;
    private JPanel proteinPanel;
    private JPanel submitPanel;
    private JButton submit;
    private JTextField nameValue;
    private JTextField caloriesValue;
    private JTextField carbohydratesValue;
    private JTextField fatsValue;
    private JTextField proteinValue;
    private JFrame addMealFrame;

    public MealsButton() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        JScrollPane listScrollPane = new JScrollPane(allMeals);
        listPanel.add(listScrollPane);
        frame.add(listPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addMeal = new JButton("Add Meal");
        addMeal.addActionListener(this);
        buttonPanel.add(addMeal, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMeal) {
            NewMealDetails newMeal = new NewMealDetails();
        }
    }

    class NewMealDetails {
        public NewMealDetails() {
            addMealFrame = new JFrame();
            addMealFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addMealFrame.setSize(new Dimension(300, 200));
            addMealFrame.setVisible(true);
            addMealFrame.setLayout(new BorderLayout());

            JPanel macrosPanel = new JPanel();
            macrosPanel.setLayout(new BoxLayout(macrosPanel, BoxLayout.Y_AXIS));
            namePanel();
            caloriesPanel();
            carbsPanel();
            fatsPanel();
            proteinPanel();
            macrosPanel.add(namePanel);
            macrosPanel.add(caloriesPanel);
            macrosPanel.add(carbsPanel);
            macrosPanel.add(fatsPanel);
            macrosPanel.add(proteinPanel);

            submitPanel = new JPanel();
            pressedSubmit();
            submitPanel.add(submit);

            addMealFrame.add(macrosPanel, BorderLayout.NORTH);
            addMealFrame.add(submitPanel, BorderLayout.SOUTH);
        }

        public void namePanel() {
            namePanel = new JPanel();
            JLabel name = new JLabel("Meal Name:");
            nameValue = new JTextField(10);
            namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
            namePanel.add(name);
            namePanel.add(nameValue);
        }

        public void caloriesPanel() {
            caloriesPanel = new JPanel();
            JLabel calories = new JLabel("Calories:");
            caloriesValue = new JTextField(10);
            caloriesPanel.setLayout(new BoxLayout(caloriesPanel, BoxLayout.LINE_AXIS));
            caloriesPanel.add(calories);
            caloriesPanel.add(caloriesValue);
        }

        public void carbsPanel() {
            carbsPanel = new JPanel();
            JLabel carbohydrates = new JLabel("Carbohydrates:");
            carbohydratesValue = new JTextField(10);
            carbsPanel.setLayout(new BoxLayout(carbsPanel, BoxLayout.LINE_AXIS));
            carbsPanel.add(carbohydrates);
            carbsPanel.add(carbohydratesValue);
        }

        public void fatsPanel() {
            fatsPanel = new JPanel();
            JLabel fats = new JLabel("Fats:");
            fatsValue = new JTextField(10);
            fatsPanel.setLayout(new BoxLayout(fatsPanel, BoxLayout.LINE_AXIS));
            fatsPanel.add(fats);
            fatsPanel.add(fatsValue);
        }

        public void proteinPanel() {
            proteinPanel = new JPanel();
            JLabel protein = new JLabel("Protein:");
            proteinValue = new JTextField(10);
            proteinPanel.setLayout(new BoxLayout(proteinPanel, BoxLayout.LINE_AXIS));
            proteinPanel.add(protein);
            proteinPanel.add(proteinValue);
        }
    }

    public void pressedSubmit() {
        submit = new JButton("Submit");
        addMealListener = new AddMealListener(submit);
        submit.setActionCommand("Submit");
        submit.addActionListener(addMealListener);
        submit.setEnabled(true);
    }

    class AddMealListener implements ActionListener, DocumentListener {

        private JButton button;
        private boolean isEnabled = true;

        public AddMealListener(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {
            String mealName = nameValue.getText();
            String caloriesValueText = caloriesValue.getText();
            String carbohydratesValueText = carbohydratesValue.getText();
            String fatsValueText = fatsValue.getText();
            String proteinValueText = proteinValue.getText();

            if ("Submit".equals(e.getActionCommand())) {
                listModel.addElement("Meal: " + mealName);
                listModel.addElement("Calories: " + caloriesValueText);
                listModel.addElement("Carbohydates: " + carbohydratesValueText);
                listModel.addElement("Fats: " + fatsValueText);
                listModel.addElement("Protein: " + proteinValueText);
                listModel.addElement(" ");
            }
            addMealFrame.dispose();
        }

        public void insertUpdate(DocumentEvent e) {
            if (!isEnabled) {
                button.setEnabled(true);
            }
        }

        public void removeUpdate(DocumentEvent e) {
            doEmptyTextField(e);
        }

        public void changedUpdate(DocumentEvent e) {
            if (!doEmptyTextField(e)) {
                if (!isEnabled) {
                    button.setEnabled(true);
                }
            }
        }

        public boolean doEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                isEnabled = false;
                return true;
            }
            return false;
        }
    }
}
