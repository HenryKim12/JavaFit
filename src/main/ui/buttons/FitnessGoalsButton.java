package ui.buttons;

import ui.JavaFit;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

// Represents the frame that opens when pressing the FitnessGoal Button on main menu
// based on ListDemo project
public class FitnessGoalsButton extends JPanel implements ListSelectionListener {

    private JFrame frame;
    protected JList listOfGoals;
    protected DefaultListModel listModel;
    private JButton addButton;
    protected JButton completedButton;
    private JTextField newGoalText;
    private AddGoalListener addGoalListener;

    // EFFECTS: creates the frame for the fitness goals button
    public FitnessGoalsButton() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(420, 420));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        setUpFrame();
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

//        add(addPanel, BorderLayout.NORTH);
//        add(listScrollPane, BorderLayout.CENTER);
//        add(completedPanel, BorderLayout.SOUTH);

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

    }
}
