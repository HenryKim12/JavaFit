package ui;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import model.*;

import java.util.Scanner;

// JavaFit application
// based on Teller app; link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public class JavaFitApp {

    private Workout push;
    private Workout pull;
    private Workout legs;
    private ListOfFitnessGoals fitnessGoals;
    private DailyMeals meals;
    private Scanner input;

    // EFFECTS: runs the JavaFit application
    public JavaFitApp() {
        runJavaFit();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runJavaFit() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Exiting JavaFit.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("e")) {
            addExercise();
        } else if (command.equals("f")) {
            addFitnessGoal();
        } else if (command.equals("m")) {
            addMeal();
        } else if (command.equals("v")) {
            viewAll();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes profile
    private void init() {
        push = new Workout("Push Day");
        pull = new Workout("Pull Day");
        legs = new Workout("Leg Day");
        fitnessGoals = new ListOfFitnessGoals();
        meals = new DailyMeals();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\te -> add exercise");
        System.out.println("\tf -> add fitness goal");
        System.out.println("\tm -> add meal");
        System.out.println("\tv -> view all");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds an exercise to the workout
    private void addExercise() {
        Workout selected = selectWorkout();
        System.out.println("Enter name of exercise to add:");
        input.nextLine();
        String exercise = input.nextLine();
        System.out.println("Enter muscle group:");
        String muscle = input.nextLine();
        System.out.println("Number of sets:");
        int sets = input.nextInt();
        System.out.println("Number of reps:");
        int reps = input.nextInt();
        Exercise e = new Exercise(exercise, muscle, sets, reps);
        selected.addExercise(e);

        printWorkout(selected);
    }

    // MODIFIES: this
    // EFFECTS: adds a fitness goal to the list
    private void addFitnessGoal() {
        System.out.println("Enter a new fitness goal:");
        input.nextLine();
        String fitnessGoal = input.nextLine();
        FitnessGoal fg = new FitnessGoal(fitnessGoal);
        this.fitnessGoals.addFitnessGoal(fg);

        printListOfFitnessGoals();
    }

    // MODIFIES: this
    // EFFECTS: adds the meal including its macros
    private void addMeal() {
        System.out.println("Enter your meal:");
        input.nextLine();
        String food = input.nextLine();
        System.out.println("Number of calories:");
        int calories = input.nextInt();
        System.out.println("Amount of carbohydrates in grams:");
        int carbohydrates = input.nextInt();
        System.out.println("Amount of fats in grams:");
        int fats = input.nextInt();
        System.out.println("Amount of protein in grams:");
        int protein = input.nextInt();
        Meal m = new Meal(food, calories, carbohydrates, fats, protein);
        this.meals.addMeal(m);

        printTotalDailyIntake();
    }

    // EFFECTS: displays the current workout, fitness goals, and daily meals
    private void viewAll() {
        String selection = "";
        System.out.println("What do you wish to view?");

        while (!(selection.equals("workouts") || selection.equals("fitness goals") || selection.equals("meals"))) {
            System.out.println("-" + " " + "workouts");
            System.out.println("-" + " " + "fitness goals");
            System.out.println("-" + " " + "meals");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("workouts")) {
            printWorkout(selectWorkout());
        } else if (selection.equals("fitness goals")) {
            printListOfFitnessGoals();
        } else {
            printTotalDailyIntake();
        }
    }

    // EFFECTS: prompts the user to select the type of workout and returns it
    private Workout selectWorkout() {
        String selection = "";
        System.out.println("Select workout:");

        while (!(selection.equals("push") || selection.equals("pull") || selection.equals("legs"))) {
            System.out.println("-" + " " + "push");
            System.out.println("-" + " " + "pull");
            System.out.println("-" + " " + "legs");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("push")) {
            return push;
        } else if (selection.equals("pull")) {
            return pull;
        } else {
            return legs;
        }
    }

    // EFFECTS: prints the workout to the screen
    private void printWorkout(Workout selected) {
        System.out.println("Today's workout: ");
        System.out.println(" ");
        for (Exercise e : selected.getWorkout()) {
            System.out.println("-" + " " + e.getName() + " " + e.getSets() + "x" + e.getReps());
        }
    }

    // EFFECTS: prints all fitness goals to the screen
    private void printListOfFitnessGoals() {
        System.out.println("All my fitness goals:");
        System.out.println(" ");
        for (int i = 0; i <= this.fitnessGoals.getAllFitnessGoals().size() - 1; i++) {
            System.out.println("-" + " " + this.fitnessGoals.getFitnessGoal(i));
        }
    }

    // EFFECTS: prints out all meals and macros for that day
    private void printTotalDailyIntake() {
        System.out.println("Today's meals:");
        System.out.println(" ");
        for (int i = 0; i <= this.meals.getDailyMeals().size() - 1; i++) {
            System.out.println("Meal #" + (i + 1));
            System.out.println("Food: " + this.meals.getMeal(i).getName());
            System.out.println("Calories: " + this.meals.getMeal(i).getCalories());
            System.out.println("Carbohydrates: " + this.meals.getMeal(i).getCarbohydrates());
            System.out.println("Fats: " + this.meals.getMeal(i).getFats());
            System.out.println("Protein: " + this.meals.getMeal(i).getProtein());
            System.out.println(" ");
        }

        System.out.println(" ");
        System.out.println("Total Calories: " + this.meals.getTotalCalories());
        System.out.println("Total Carbohydrates: " + this.meals.getTotalCarbohydrates());
        System.out.println("Total Fats: " + this.meals.getTotalFats());
        System.out.println("Total Protein: " + this.meals.getTotalProtein());
    }
}
