package ui.buttons;

import model.FitnessGoal;
import model.ListOfFitnessGoals;
import persistence.JsonReader;
import ui.FitnessGoalsListModel;
import ui.Main;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

// Represents the frame that opens when pressing the FitnessGoal Button on main menu
// based on ListDemo project
public class FitnessGoalsButton implements ActionListener, ListSelectionListener {

    private JFrame frame;
    private FitnessGoalsListModel listModel;
    private JList listOfGoals;
    private JButton addButton;
    private JButton completedButton;
    private JTextField newGoalText;
    private AddGoalListener addGoalListener;
    private JMenuItem loadFitnessGoals;
    private JMenuItem saveFitnessGoals;
    private JMenuItem returnToMainMenu;
    private ListOfFitnessGoals fitnessGoals;

    // EFFECTS: creates the frame for the fitness goals button
    public FitnessGoalsButton(ListOfFitnessGoals fitnessGoals) {
        this.fitnessGoals = fitnessGoals;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(420, 420));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        listModel = new FitnessGoalsListModel(fitnessGoals);
        listOfGoals = new JList(listModel);

        setUpFrame();
        menuSetUp();
    }

    public void menuSetUp() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        loadFitnessGoals = new JMenuItem("Load Fitness Goals");
        loadFitnessGoals.addActionListener(this);
        saveFitnessGoals = new JMenuItem("Save Fitness Goals");
        saveFitnessGoals.addActionListener(this);
        returnToMainMenu = new JMenuItem("Return to Main Menu");

        menu.add(loadFitnessGoals);
        menu.add(saveFitnessGoals);
        frame.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadFitnessGoals) {
            Main.loadFitnessGoals();
        } else if (e.getSource() == saveFitnessGoals) {
            Main.saveFitnessGoals();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the panels and layout for the frame
    public void setUpFrame() {
//        listModel = new FitnessGoalsListModel();

        //Create the list and put it in a scroll pane.
//        listOfGoals = new JList(listModel);
        listOfGoals.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listOfGoals.setSelectedIndex(0);
        listOfGoals.addListSelectionListener(this);
        listOfGoals.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(listOfGoals);

        addToList();
        removeFromList();

        newGoalText = new JTextField(10);
        newGoalText.addActionListener(addGoalListener);
        newGoalText.getDocument().addDocumentListener(addGoalListener);

        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.LINE_AXIS));
        addPanel.add(addButton);
        addPanel.add(newGoalText);

        JPanel completedPanel = new JPanel();
        completedPanel.setLayout(new BoxLayout(completedPanel, BoxLayout.LINE_AXIS));
        completedPanel.add(completedButton);

        frame.add(addPanel, BorderLayout.NORTH);
        frame.add(listScrollPane, BorderLayout.CENTER);
        frame.add(completedPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: creates the add button that allows you to add a fitness goal
    public void addToList() {
        addButton = new JButton("Add New Goal!");
        addGoalListener = new AddGoalListener(addButton);
        addButton.setActionCommand("Add New Goal!");
        addButton.addActionListener(addGoalListener);
        addButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: creates the completed button that allows you to remove a fitness goal
    public void removeFromList() {
        completedButton = new JButton("Completed!");
        completedButton.setActionCommand("Completed!");
        completedButton.addActionListener(new CompletedGoalListener());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (listOfGoals.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                completedButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                completedButton.setEnabled(true);
            }
        }
    }

    class AddGoalListener implements ActionListener, DocumentListener {

        private JButton button;
        private boolean isEnabled = false;

        public AddGoalListener(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {
            String newGoal = newGoalText.getText();

//            if (newGoal.equals("") || listModel.contains(newGoal)) {
//            if (newGoal.equals("")) {
//                Toolkit.getDefaultToolkit().beep();
//                newGoalText.requestFocusInWindow();
//                newGoalText.selectAll();
//                return;
//            }

            listModel.addFitnessGoal(newGoalText.getText());
//            listModel.insertElementAt(new FitnessGoal(newGoalText.getText()), listModel.size());

//            newGoalText.requestFocusInWindow();
            newGoalText.setText("");
//
//            listOfGoals.setSelectedIndex(listModel.getSize() - 1);
//            listOfGoals.ensureIndexIsVisible(listModel.getSize() - 1);
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

        private boolean doEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                isEnabled = false;
                return true;
            }
            return false;
        }
    }

    class CompletedGoalListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selected = listOfGoals.getSelectedIndex();
            listModel.removeFitnessGoalAt(selected);

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
}
