package ui.buttons;

import model.ListOfFitnessGoals;
import ui.listmodels.FitnessGoalsListModel;
import ui.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Represents the frame that opens when pressing the Fitness Goal Button on main menu
public class FitnessGoalsGUI {

    private JFrame frame;
    private FitnessGoalsListModel listModel;
    private JList listOfGoals;
    private JButton addButton;
    private JButton completedButton;
    private JTextField newGoal;
    private JMenuItem loadFitnessGoals;
    private JMenuItem saveFitnessGoals;
    private ListOfFitnessGoals fitnessGoals;

    // EFFECTS: creates the frame for the fitness goals button
    public FitnessGoalsGUI(ListOfFitnessGoals fitnessGoals) {
        this.fitnessGoals = fitnessGoals;

        frame = new JFrame("JavaFit");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(420, 420));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        setUpAddButton();
        setUpNewGoalField();
        JPanel addPanel = new JPanel();
        addPanel.add(addButton);
        addPanel.add(newGoal);
        frame.add(addPanel, BorderLayout.NORTH);

        setUpList();
        setUpCompletedButton();
        setUpMenu();
    }

    // MODIFIES: this, fitnessGoals
    // EFFECTS: creates the add fitness goal button and allows you to add a new goal
    public void setUpAddButton() {
        addButton = new JButton("Add New Fitness Goal");

        // add the fitness goal by pressing the add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.addFitnessGoal(newGoal.getText());
                newGoal.setText("");
            }
        });
    }

    // MODIFIES: this, fitnessGoals
    // EFFECTS: creates the text field for the new goal to be typed and added
    public void setUpNewGoalField() {
        newGoal = new JTextField(10);
        newGoal.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // not needed
            }

            // add the fitness goal by pressing "enter"
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    listModel.addFitnessGoal(newGoal.getText());
                    newGoal.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // not needed
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates the list that contains all fitness goals
    public void setUpList() {
        listModel = new FitnessGoalsListModel(fitnessGoals);
        listOfGoals = new JList(listModel);
        listOfGoals.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listOfGoals.setSelectedIndex(0);
        listOfGoals.setVisibleRowCount(5);
        listOfGoals.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (listOfGoals.getSelectedIndex() == -1) {
                        //No selection, disable completed button.
                        completedButton.setEnabled(false);

                    } else {
                        //Selection, enable the completed button.
                        completedButton.setEnabled(true);
                    }
                }
            }
        });
        JScrollPane listScrollPane = new JScrollPane(listOfGoals);
        frame.add(listScrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this, fitnessGoals
    // EFFECTS: creates the completed button for when a goal has been accomplished
    public void setUpCompletedButton() {
        completedButton = new JButton("Completed Goal!");
        completedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = listOfGoals.getSelectedIndex();
                if (i >= 0) {
                    listModel.removeFitnessGoalAt(i);
                }
            }
        });

        frame.add(completedButton, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: creates the menu bar that allow loading and saving of fitness goals
    public void setUpMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        loadFitnessGoals = new JMenuItem("Load Fitness Goals");
        loadFitnessGoals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.loadFitnessGoals();
            }
        });

        saveFitnessGoals = new JMenuItem("Save Fitness Goals");
        saveFitnessGoals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.saveFitnessGoals();
                confirmSaved();
            }
        });

        menu.add(loadFitnessGoals);
        menu.add(saveFitnessGoals);
        frame.setJMenuBar(menuBar);
    }

    // EFFECTS: opens a window to show that saving was done properly
    public void confirmSaved() {
        JFrame savedFrame = new JFrame();
        savedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        savedFrame.setSize(new Dimension(200, 100));
        savedFrame.setVisible(true);
        savedFrame.setLayout(new BorderLayout());
        JLabel savedMessage = new JLabel("Saved fitness goals to file");
        savedFrame.add(savedMessage);
    }

    // EFFECTS: updates fitnessGoals from listModel
    public void updateFitnessGoalsGUI(ListOfFitnessGoals fitnessGoals) {
        listModel.updateFitnessGoals(fitnessGoals);
    }
}
