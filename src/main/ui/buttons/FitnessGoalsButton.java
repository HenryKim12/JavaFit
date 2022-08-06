package ui.buttons;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// Represents the frame that opens when pressing the FitnessGoal Button on main menu
// based on ListDemo project
public class FitnessGoalsButton implements ActionListener, ListSelectionListener {

    private JFrame frame;
    private JList listOfGoals;
    private DefaultListModel listModel;
    private JButton addButton;
    private JButton completedButton;
    private JTextField newGoalText;
    private AddGoalListener addGoalListener;
    private JMenuItem loadFitnessGoals;
    private JMenuItem saveFitnessGoals;

    // EFFECTS: creates the frame for the fitness goals button
    public FitnessGoalsButton() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(420, 420));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        setUpFrame();
        menuSetUp();
    }

    public void menuSetUp() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);
        JMenu loadSubMenu = new JMenu("Load File");
        loadSubMenu.setMnemonic(KeyEvent.VK_S);
        loadFitnessGoals = new JMenuItem("Load Fitness Goals");
        loadSubMenu.add(loadFitnessGoals);

        JMenu saveSubMenu = new JMenu("Save File");
        saveSubMenu.setMnemonic(KeyEvent.VK_S);
        saveFitnessGoals = new JMenuItem("Save Fitness Goals");

        menu.add(loadSubMenu);
        loadSubMenu.addActionListener(this);
        menu.add(saveSubMenu);
        saveSubMenu.addActionListener(this);
        frame.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadFitnessGoals) {
            //
        } else if (e.getSource() == saveFitnessGoals) {
//            frame.dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the panels and layout for the frame
    public void setUpFrame() {
        listModel = new DefaultListModel();

        //Create the list and put it in a scroll pane.
        listOfGoals = new JList(listModel);
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

            if (newGoal.equals("") || listModel.contains(newGoal)) {
                Toolkit.getDefaultToolkit().beep();
                newGoalText.requestFocusInWindow();
                newGoalText.selectAll();
                return;
            }

            listModel.insertElementAt(newGoalText.getText(), listModel.size());

            newGoalText.requestFocusInWindow();
            newGoalText.setText("");

            listOfGoals.setSelectedIndex(listModel.size() - 1);
            listOfGoals.ensureIndexIsVisible(listModel.size() - 1);
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
            listModel.remove(selected);

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
