package ui;

import persistence.JsonReader;
import ui.buttons.FitnessGoalsButton;
import ui.buttons.MealsButton;
import ui.buttons.WorkoutsButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class JavaFit extends Component implements ActionListener {

    private JFrame frame;
    private JPanel buttonPanel;
    private JButton workoutsButton;
    private JButton fitnessGoalsButton;
    private JButton mealsButton;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu saveSubMenu;
    private JMenu loadSubMenu;
    private JMenuItem loadPush;
    private JMenuItem loadPull;
    private JMenuItem loadLegs;
    private JMenuItem loadGoals;
    private JMenuItem loadMeals;
    private JMenuItem savePush;
    private JMenuItem savePull;
    private JMenuItem saveLegs;
    private JMenuItem saveGoals;
    private JMenuItem saveMeals;

    public JavaFit() {
        //Create and set up the window.
        frame = new JFrame("JavaFit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 420));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        JLabel title = new JLabel("Welcome To JavaFit!", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        frame.add(title, BorderLayout.NORTH);

        //image
        String imagePath = "C:\\Users\\henry\\OneDrive\\Documents\\2nd Year\\CPSC 210\\JavaFitLogo.jpg";
        frame.add(new JLabel(new ImageIcon(imagePath)));

        // graph panel area
//        JPanel graphPanel = new JPanel();
//        CategoryAxis categoryAxisX = new CategoryAxis();
//        NumberAxis numberAxisY = new NumberAxis();
//        BarChart<String, Number> barChart = new BarChart(categoryAxisX, numberAxisY);
//        categoryAxisX.setLabel("Category");
//        numberAxisY.setLabel("Number");
//        barChart.setTitle("Calories");
//        categoryAxisX.setLabel("Category");
//        numberAxisY.setLabel("Value");

        addingButtonsToMainFrame();
        menuSetUp();
    }

    public void menuSetUp() {
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);
        loadSubMenu = new JMenu("Load File");
        loadSubMenu.setMnemonic(KeyEvent.VK_S);
        loadMenuSetUp();

        saveSubMenu = new JMenu("Save File");
        saveSubMenu.setMnemonic(KeyEvent.VK_S);
        saveMenuSetUp();

        menu.add(loadSubMenu);
        loadSubMenu.addActionListener(this);
        menu.add(saveSubMenu);
        saveSubMenu.addActionListener(this);
        frame.setJMenuBar(menuBar);
    }

    public void loadMenuSetUp() {
        loadPush = new JMenuItem("Load Push Workout", KeyEvent.VK_T);
        loadPull = new JMenuItem("Load Pull Workout", KeyEvent.VK_T);
        loadLegs = new JMenuItem("Load Legs Workout", KeyEvent.VK_T);
        loadGoals = new JMenuItem("Load Fitness Goals", KeyEvent.VK_T);
        loadMeals = new JMenuItem("Load Meals", KeyEvent.VK_T);
        loadPush.addActionListener(this);
        loadSubMenu.add(loadPush);
        loadPull.addActionListener(this);
        loadSubMenu.add(loadPull);
        loadLegs.addActionListener(this);
        loadSubMenu.add(loadLegs);
        loadGoals.addActionListener(this);
        loadSubMenu.add(loadGoals);
        loadMeals.addActionListener(this);
        loadSubMenu.add(loadMeals);
    }

    public void saveMenuSetUp() {
        savePush = new JMenuItem("Save Push Workout", KeyEvent.VK_T);
        savePull = new JMenuItem("Save Pull Workout", KeyEvent.VK_T);
        saveLegs = new JMenuItem("Save Legs Workout", KeyEvent.VK_T);
        saveGoals = new JMenuItem("Save Fitness Goals", KeyEvent.VK_T);
        saveMeals = new JMenuItem("Save Meals", KeyEvent.VK_T);
        saveSubMenu.add(savePush);
        saveSubMenu.add(savePull);
        saveSubMenu.add(saveLegs);
        saveSubMenu.add(saveGoals);
        saveSubMenu.add(saveMeals);
    }

    public void addingButtonsToMainFrame() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        workoutsButton = new JButton("Workouts");
        workoutsButton.addActionListener(this);

        fitnessGoalsButton = new JButton("Fitness Goals");
        fitnessGoalsButton.addActionListener(this);

        mealsButton = new JButton("Meals");
        mealsButton.addActionListener(this);

        buttonPanel.add(workoutsButton);
        buttonPanel.add(fitnessGoalsButton);
        buttonPanel.add(mealsButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JavaFit();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == workoutsButton) {
//            frame.dispose();
            WorkoutsButton workoutsButton = new WorkoutsButton();
        } else if (e.getSource() == fitnessGoalsButton) {
//            frame.dispose();
            FitnessGoalsButton fitnessGoalsButton = new FitnessGoalsButton();
        } else if (e.getSource() == mealsButton) {
//            frame.dispose();
            MealsButton mealsButton = new MealsButton();
        } else if (e.getSource() == loadPush) {
//            loadPushWorkout();
        } else if (e.getSource() == savePush) {
            //
        }
    }
}