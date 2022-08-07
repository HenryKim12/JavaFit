package ui;

import model.ListOfFitnessGoals;
import ui.buttons.FitnessGoalsButton;
import ui.buttons.MealsButton;
import ui.buttons.WorkoutsButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JavaFit extends Component implements ActionListener {

    private JFrame frame;
    private JPanel buttonPanel;
    private JButton workoutsButton;
    private JButton fitnessGoalsButton;
    private JButton mealsButton;
    private ListOfFitnessGoals fitnessGoals;

    public JavaFit(ListOfFitnessGoals fitnessGoals) {
        this.fitnessGoals = fitnessGoals;
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

//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new JavaFit();
//            }
//        });
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == workoutsButton) {
//            frame.dispose();
            WorkoutsButton workoutsButton = new WorkoutsButton();
        } else if (e.getSource() == fitnessGoalsButton) {
//            frame.dispose();
            FitnessGoalsButton fitnessGoalsButton = new FitnessGoalsButton(fitnessGoals);
        } else if (e.getSource() == mealsButton) {
//            frame.dispose();
            MealsButton mealsButton = new MealsButton();
        }
    }
}