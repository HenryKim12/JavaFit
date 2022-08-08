package ui.buttons;

import model.DailyMeals;
import model.Meal;
import ui.Main;
import ui.listmodels.MealsListModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// Represent the frame for the meals button on main menu
public class MealsGUI {

    private JFrame frame;
    private JList allMealsList;
    private MealsListModel listModel;
    private JButton addMealButton;
    private JButton removeMealButton;
    private JLabel caption;
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
    private DailyMeals meals;
    private JMenuItem loadMeals;
    private JMenuItem saveMeals;

    // EFFECTS: constructs the frame for meals
    public MealsGUI(DailyMeals meals) {
        this.meals = meals;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(420, 420));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        setUpAllMealsCaption();
        setUpMealsList();
        setUpAddMealButton();
        setUpRemoveMealButton();
        setUpMenu();

        JPanel editPanel = new JPanel();
        editPanel.add(addMealButton);
        editPanel.add(removeMealButton);

        frame.add(caption, BorderLayout.NORTH);
        frame.add(editPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: creates the caption for the frame
    public void setUpAllMealsCaption() {
        caption = new JLabel("All Meals:");
    }

    // MODIFIES: this
    // EFFECTS: creates the list of meals
    public void setUpMealsList() {
        listModel = new MealsListModel(meals);
        allMealsList = new JList(listModel);
        JScrollPane listScrollPane = new JScrollPane(allMealsList);
        frame.add(listScrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this, meals
    // EFFECTS: creates and implements the add meal button
    public void setUpAddMealButton() {
        addMealButton = new JButton("Add Meal");
        addMealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addMealButton) {
                    NewMealDetails newMeal = new NewMealDetails();
                }
            }
        });
    }

    // MODIFIES: this, meals
    // EFFECTS: creates and implements the remove meal button
    public void setUpRemoveMealButton() {
        removeMealButton = new JButton("Remove Meal");
        removeMealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = allMealsList.getSelectedIndex();
                if (i >= 0) {
                    listModel.removeMealAt(i);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates the menu for frame that allows for load and save of meals
    public void setUpMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        loadMeals = new JMenuItem("Load Meals");
        loadMeals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.loadDailyMeals();
            }
        });

        saveMeals = new JMenuItem("Save Meals");
        saveMeals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.saveDailyMeals();
                confirmSaved();
            }
        });

        menu.add(loadMeals);
        menu.add(saveMeals);
        frame.setJMenuBar(menuBar);
    }

    // EFFECTS: opens a window to show that saving was done properly
    public void confirmSaved() {
        JFrame savedFrame = new JFrame();
        savedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        savedFrame.setSize(new Dimension(200, 100));
        savedFrame.setVisible(true);
        savedFrame.setLayout(new BorderLayout());
        JLabel savedMessage = new JLabel("Saved meals to file");
        savedFrame.add(savedMessage);
    }

    // Represents the frame that opens when pressing add meal button
    class NewMealDetails {

        // EFFECTS: creates the frame
        public NewMealDetails() {
            addMealFrame = new JFrame("JavaFit");
            addMealFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addMealFrame.setSize(new Dimension(300, 200));
            addMealFrame.setVisible(true);
            addMealFrame.setLayout(new BorderLayout());

            // macros of meal
            JPanel mealInfoPanel = new JPanel();
            mealInfoPanel.setLayout(new BoxLayout(mealInfoPanel, BoxLayout.Y_AXIS));

            setUpMealNamePanel();
            setUpCaloriesPanel();
            setUpCarbohydratesPanel();
            setUpFatsPanel();
            setUpProteinPanel();

            mealInfoPanel.add(namePanel);
            mealInfoPanel.add(caloriesPanel);
            mealInfoPanel.add(carbsPanel);
            mealInfoPanel.add(fatsPanel);
            mealInfoPanel.add(proteinPanel);

            // submit button
            submitPanel = new JPanel();
            setUpSubmitButton();
            submitPanel.add(submit);

            addMealFrame.add(mealInfoPanel, BorderLayout.NORTH);
            addMealFrame.add(submitPanel, BorderLayout.SOUTH);
        }

        // MODIFIES: this
        // EFFECTS: creates the panel for meal name
        public void setUpMealNamePanel() {
            namePanel = new JPanel();
            namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));

            JLabel name = new JLabel("Meal Name:");
            nameValue = new JTextField(10);
            namePanel.add(name);
            namePanel.add(nameValue);
        }

        // MODIFIES: this
        // EFFECTS: creates the panel for meal calories
        public void setUpCaloriesPanel() {
            caloriesPanel = new JPanel();
            caloriesPanel.setLayout(new BoxLayout(caloriesPanel, BoxLayout.LINE_AXIS));

            JLabel calories = new JLabel("Calories:");
            caloriesValue = new JTextField(10);
            caloriesPanel.add(calories);
            caloriesPanel.add(caloriesValue);
        }

        // MODIFIES: this
        // EFFECTS: creates the panel for meal carbs
        public void setUpCarbohydratesPanel() {
            carbsPanel = new JPanel();
            carbsPanel.setLayout(new BoxLayout(carbsPanel, BoxLayout.LINE_AXIS));

            JLabel carbohydrates = new JLabel("Carbohydrates:");
            carbohydratesValue = new JTextField(10);
            carbsPanel.add(carbohydrates);
            carbsPanel.add(carbohydratesValue);
        }

        // MODIFIES: this
        // EFFECTS: creates the panel for meal fats
        public void setUpFatsPanel() {
            fatsPanel = new JPanel();
            fatsPanel.setLayout(new BoxLayout(fatsPanel, BoxLayout.LINE_AXIS));

            JLabel fats = new JLabel("Fats:");
            fatsValue = new JTextField(10);
            fatsPanel.add(fats);
            fatsPanel.add(fatsValue);
        }

        // MODIFIES: this
        // EFFECTS: creates the panel for meal protein
        public void setUpProteinPanel() {
            proteinPanel = new JPanel();
            proteinPanel.setLayout(new BoxLayout(proteinPanel, BoxLayout.LINE_AXIS));

            JLabel protein = new JLabel("Protein:");
            proteinValue = new JTextField(10);
            proteinPanel.add(protein);
            proteinPanel.add(proteinValue);
        }
    }

    // MODIFIES: this, listModel
    // EFFECTS: creates the submit button
    public void setUpSubmitButton() {
        submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.addMeal(nameValue.getText(), caloriesValue.getText(), carbohydratesValue.getText(),
                        fatsValue.getText(), proteinValue.getText());
                addMealFrame.dispose();
            }
        });
    }

    // EFFECTS: updates meals from listModel
    public void updateMealsGUI(DailyMeals meals) {
        listModel.updateMeals(meals);
    }
}
