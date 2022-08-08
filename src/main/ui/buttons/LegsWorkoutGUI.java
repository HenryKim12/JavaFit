package ui.buttons;

import model.Exercise;
import model.Workout;
import ui.Main;
import ui.listmodels.LegsWorkoutListModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// Represents the frame that opens when pressing Legs Workout in workout GUI
public class LegsWorkoutGUI {

    private JFrame frame;
    private JLabel caption;
    private JButton addLegsExerciseButton;
    private LegsWorkoutListModel listModel;
    private JList listOfLegsExercises;
    private JPanel namePanel;
    private JPanel musclePanel;
    private JPanel setsPanel;
    private JPanel repsPanel;
    private JTextField nameValue;
    private JTextField muscleValue;
    private JTextField setsValue;
    private JTextField repsValue;
    private JPanel submitPanel;
    private JButton submit;
    private JMenuItem loadLegsExercises;
    private JMenuItem saveLegsExercises;
    private Workout legs;

    // EFFECTS: constructs the frame for legs workout
    public LegsWorkoutGUI(Workout legs) {
        this.legs = legs;

        frame = new JFrame("JavaFit");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(420, 420));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        setUpCaption();
        setUpLegsWorkoutList();
        setUpAddLegsExercise();
        setUpMenu();

        frame.add(caption, BorderLayout.NORTH);
        frame.add(addLegsExerciseButton, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: creates the caption at the top
    public void setUpCaption() {
        caption = new JLabel("Legs Workout:");
    }

    // MODIFIES: this
    // EFFECTS: creates the legs workout list
    public void setUpLegsWorkoutList() {
        listModel = new LegsWorkoutListModel(legs);
        listOfLegsExercises = new JList(listModel);
        JScrollPane listScrollPane = new JScrollPane(listOfLegsExercises);
        frame.add(listScrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates and implements the add exercise button
    public void setUpAddLegsExercise() {
        addLegsExerciseButton = new JButton("Add New Legs Exercise");
        addLegsExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewLegsExerciseDetails exerciseAdded = new NewLegsExerciseDetails();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates and implements the menu bar to allow for loading and saving of legs workout
    public void setUpMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        loadLegsExercises = new JMenuItem("Load Legs Exercises");
        loadLegsExercises.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.loadLegsWorkout();
            }
        });

        saveLegsExercises = new JMenuItem("Save Legs Exercises");
        saveLegsExercises.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.saveLegsWorkout();
                confirmSaved();
            }
        });

        menu.add(loadLegsExercises);
        menu.add(saveLegsExercises);
        frame.setJMenuBar(menuBar);
    }

    public void confirmSaved() {
        JFrame savedFrame = new JFrame();
        savedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        savedFrame.setSize(new Dimension(200, 100));
        savedFrame.setVisible(true);
        savedFrame.setLayout(new BorderLayout());
        JLabel savedMessage = new JLabel("Saved " + legs.getWorkoutTitle() + " to file");
        savedFrame.add(savedMessage);
    }

    // Represents the frame that opens when adding a new exercise
    class NewLegsExerciseDetails {

        private JFrame newExerciseFrame;

        // EFFECTS: construct the frame
        public NewLegsExerciseDetails() {
            newExerciseFrame = new JFrame();
            newExerciseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newExerciseFrame.setSize(new Dimension(300, 200));
            newExerciseFrame.setVisible(true);
            newExerciseFrame.setLayout(new BorderLayout());

            JPanel exerciseInfoPanel = new JPanel();
            exerciseInfoPanel.setLayout(new BoxLayout(exerciseInfoPanel, BoxLayout.Y_AXIS));

            setUpExerciseNamePanel();
            setUpExerciseMusclePanel();
            setUpExerciseSetsPanel();
            setUpExerciseRepsPanel();

            exerciseInfoPanel.add(namePanel);
            exerciseInfoPanel.add(musclePanel);
            exerciseInfoPanel.add(setsPanel);
            exerciseInfoPanel.add(repsPanel);

            submitPanel = new JPanel();
            setUpSubmitButton();
            submitPanel.add(submit);

            newExerciseFrame.add(exerciseInfoPanel, BorderLayout.NORTH);
            newExerciseFrame.add(submitPanel, BorderLayout.SOUTH);
        }

        // MODIFIES: this
        // EFFECTS: creates the panel for exercise name
        public void setUpExerciseNamePanel() {
            namePanel = new JPanel();
            namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));

            JLabel name = new JLabel("Exercise Name:");
            nameValue = new JTextField(10);
            namePanel.add(name);
            namePanel.add(nameValue);
        }

        // MODIFIES: this
        // EFFECTS: creates the panel for exercise muscle
        public void setUpExerciseMusclePanel() {
            musclePanel = new JPanel();
            musclePanel.setLayout(new BoxLayout(musclePanel, BoxLayout.LINE_AXIS));

            JLabel muscle = new JLabel("Muscle Group:");
            muscleValue = new JTextField(10);
            musclePanel.add(muscle);
            musclePanel.add(muscleValue);
        }

        // MODIFIES: this
        // EFFECTS: creates the panel for exercise sets
        public void setUpExerciseSetsPanel() {
            setsPanel = new JPanel();
            setsPanel.setLayout(new BoxLayout(setsPanel, BoxLayout.LINE_AXIS));

            JLabel sets = new JLabel("# of Sets:");
            setsValue = new JTextField(10);
            setsPanel.add(sets);
            setsPanel.add(setsValue);
        }

        // MODIFIES: this
        // EFFECTS: creates the panel for exercise reps
        public void setUpExerciseRepsPanel() {
            repsPanel = new JPanel();
            repsPanel.setLayout(new BoxLayout(repsPanel, BoxLayout.LINE_AXIS));

            JLabel name = new JLabel("# of Reps:");
            repsValue = new JTextField(10);
            repsPanel.add(name);
            repsPanel.add(repsValue);
        }

        // MODIFIES: this
        // EFFECTS: creates the submit button
        public void setUpSubmitButton() {
            submit = new JButton("Submit");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listModel.addExercise(new Exercise(nameValue.getText(), muscleValue.getText(),
                            Integer.parseInt(setsValue.getText()), Integer.parseInt(repsValue.getText())));
                    newExerciseFrame.dispose();
                }
            });
        }
    }

    // EFFECTS: updates legs from listModel
    public void updateLegsExercisesGUI(Workout legs) {
        listModel.updateLegsExercises(legs);
    }
}
