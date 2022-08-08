package ui.buttons;

import model.Exercise;
import model.Workout;
import ui.Main;
import ui.listmodels.PushWorkoutListModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// Represents the frame that opens when pressing push workout from workouts GUI
public class PushWorkoutGUI {

    private JFrame frame;
    private JLabel caption;
    private JButton addPushExerciseButton;
    private PushWorkoutListModel listModel;
    private JList listOfPushExercises;
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
    private JMenuItem loadPushExercises;
    private JMenuItem savePushExercises;
    private Workout push;

    // EFFECTS: constructs the frame
    public PushWorkoutGUI(Workout push) {
        this.push = push;

        frame = new JFrame("JavaFit");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(420, 420));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        setUpCaption();
        setUpPushWorkoutList();
        setUpAddPushExercise();
        setUpMenu();

        frame.add(caption, BorderLayout.NORTH);
        frame.add(addPushExerciseButton, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: creates the caption
    public void setUpCaption() {
        caption = new JLabel("Push Workout:");
    }

    // MODIFIES: this
    // EFFECTS: creates the push list
    public void setUpPushWorkoutList() {
        listModel = new PushWorkoutListModel(push);
        listOfPushExercises = new JList(listModel);
        JScrollPane listScrollPane = new JScrollPane(listOfPushExercises);
        frame.add(listScrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates and implements the add push exercise button
    public void setUpAddPushExercise() {
        addPushExerciseButton = new JButton("Add New Push Exercise");
        addPushExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewPushExerciseDetails exerciseAdded = new NewPushExerciseDetails();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates the menu that allows for save and load
    public void setUpMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        loadPushExercises = new JMenuItem("Load Push Exercises");
        loadPushExercises.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.loadPushWorkout();
            }
        });

        savePushExercises = new JMenuItem("Save Push Exercises");
        savePushExercises.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.savePushWorkout();
                confirmSaved();
            }
        });

        menu.add(loadPushExercises);
        menu.add(savePushExercises);
        frame.setJMenuBar(menuBar);
    }

    public void confirmSaved() {
        JFrame savedFrame = new JFrame();
        savedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        savedFrame.setSize(new Dimension(200, 100));
        savedFrame.setVisible(true);
        savedFrame.setLayout(new BorderLayout());
        JLabel savedMessage = new JLabel("Saved " + push.getWorkoutTitle() + " to file");
        savedFrame.add(savedMessage);
    }

    // Represents the frame that opens when pressing add new exercise button
    class NewPushExerciseDetails {

        private JFrame newExerciseFrame;

        // EFFECTS: constructs the frame
        public NewPushExerciseDetails() {
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
                    listModel.addExercise(nameValue.getText(), muscleValue.getText(),setsValue.getText(),
                            repsValue.getText());
                    newExerciseFrame.dispose();
                }
            });
        }
    }

    // EFFECTS: updates push from listModel
    public void updatePushExercisesGUI(Workout push) {
        listModel.updatePushExercises(push);
    }
}
