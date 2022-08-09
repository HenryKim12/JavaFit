package ui;

import model.DailyMeals;
import model.EventLog;
import model.ListOfFitnessGoals;
import model.Workout;
import ui.buttons.FitnessGoalsGUI;
import ui.buttons.MealsGUI;
import ui.buttons.WorkoutsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// Represents the main menu of JavaFit (main frame when opening GUI)
public class JavaFit extends JFrame {

    private JFrame frame;
    private JPanel buttonPanel;
    private JButton workoutsButton;
    private JButton fitnessGoalsButton;
    private JButton mealsButton;
    private FitnessGoalsGUI fitnessGoalsGUI;
    private MealsGUI mealsGUI;
    private WorkoutsGUI workoutsGUI;
    private Workout push;
    private Workout pull;
    private Workout legs;
    private ListOfFitnessGoals fitnessGoals;
    private DailyMeals meals;

    // EFFECTS: constructs the JavaFit main menu
    public JavaFit(Workout push, Workout pull, Workout legs, ListOfFitnessGoals fitnessGoals, DailyMeals meals) {
        this.push = push;
        this.pull = pull;
        this.legs = legs;
        this.fitnessGoals = fitnessGoals;
        this.meals = meals;

        //Create and set up the window.
        frame = new JFrame("JavaFit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 500));
        frame.setLayout(new BorderLayout());

        // Welcome message
        JLabel title = new JLabel("Welcome To JavaFit!", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        frame.add(title, BorderLayout.NORTH);

        // Image (logo)
        String imagePath = "JLogo.jpg";
        frame.add(new JLabel(new ImageIcon(this.getClass().getResource(imagePath))));

        setUpWorkoutsButton();
        setUpFitnessGoalsButton();
        setUpMealsButton();
        setUpButtonPanel();

        setUpLog();

        frame.setVisible(true);
    }

    public void setUpLog() {
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }

    public void printLog(EventLog el) {
        for (model.Event next : el) {
            System.out.println(next.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: creates and implements the workouts button into main menu
    public void setUpWorkoutsButton() {
        workoutsButton = new JButton("Workouts");
        workoutsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workoutsGUI = new WorkoutsGUI(push, pull, legs);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates and implements the fitness goals button into main menu
    public void setUpFitnessGoalsButton() {
        fitnessGoalsButton = new JButton("Fitness Goals");
        fitnessGoalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fitnessGoalsGUI = new FitnessGoalsGUI(fitnessGoals);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates and implements the meals button into the main menu
    public void setUpMealsButton() {
        mealsButton = new JButton("Meals");
        mealsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mealsGUI = new MealsGUI(meals);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: assembles all the buttons at the bottom of the main menu
    public void setUpButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(workoutsButton);
        buttonPanel.add(fitnessGoalsButton);
        buttonPanel.add(mealsButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: updates push from workoutsGUI
    public void updatePushExercises(Workout push) {
        workoutsGUI.updatePushExercises(push);
    }

    // EFFECTS: updates pull from workoutsGUI
    public void updatePullExercises(Workout pull) {
        workoutsGUI.updatePullExercises(pull);
    }

    // EFFECTS: updates legs from workoutsGUI
    public void updateLegsExercises(Workout legs) {
        workoutsGUI.updateLegsExercises(legs);
    }

    // EFFECTS: updates fitnessGoals from fitnessGoalsGUI
    public void updateFitnessGoals(ListOfFitnessGoals fitnessGoals) {
        fitnessGoalsGUI.updateFitnessGoalsGUI(fitnessGoals);
    }

    // EFFECTS: updates meals from mealsGUI
    public void updateMeals(DailyMeals meals) {
        mealsGUI.updateMealsGUI(meals);
    }
}