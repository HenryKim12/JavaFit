package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// JavaFit application
// based on Teller app; link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public class JavaFitApp {
    private static final String PUSH_STORE = "./data/pushWorkout.json";
    private static final String PULL_STORE = "./data/pullWorkout.json";
    private static final String LEGS_STORE = "./data/legsWorkout.json";
    private static final String GOALS_STORE = "./data/fitnessGoals.json";
    private static final String MEALS_STORE = "./data/dailyMeals.json";
    private Workout push;
    private Workout pull;
    private Workout legs;
    private ListOfFitnessGoals fitnessGoals;
    private DailyMeals meals;
    private Scanner input;
    private JsonWriter jsonPushWriter;
    private JsonReader jsonPushReader;
    private JsonWriter jsonPullWriter;
    private JsonReader jsonPullReader;
    private JsonWriter jsonLegsWriter;
    private JsonReader jsonLegsReader;
    private JsonWriter jsonGoalsWriter;
    private JsonReader jsonGoalsReader;
    private JsonWriter jsonMealsWriter;
    private JsonReader jsonMealsReader;


    // EFFECTS: runs the JavaFit application
    public JavaFitApp() throws FileNotFoundException {
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
        if (command.equals("w")) {
            editWorkout();
        } else if (command.equals("f")) {
            addFitnessGoal();
        } else if (command.equals("m")) {
            addMeal();
        } else if (command.equals("v")) {
            viewAll();
        } else if (command.equals("l")) {
            load();
        } else if (command.equals("s")) {
            save();
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
        jsonPushWriter = new JsonWriter(PUSH_STORE);
        jsonPushReader = new JsonReader(PUSH_STORE);

        jsonPullWriter = new JsonWriter(PULL_STORE);
        jsonPullReader = new JsonReader(PULL_STORE);

        jsonLegsWriter = new JsonWriter(LEGS_STORE);
        jsonLegsReader = new JsonReader(LEGS_STORE);

        jsonGoalsWriter = new JsonWriter(GOALS_STORE);
        jsonGoalsReader = new JsonReader(GOALS_STORE);

        jsonMealsWriter = new JsonWriter(MEALS_STORE);
        jsonMealsReader = new JsonReader(MEALS_STORE);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tw -> edit workouts");
        System.out.println("\tf -> add fitness goal");
        System.out.println("\tm -> add meal");
        System.out.println("\tv -> view all");
        System.out.println("\tl -> load options");
        System.out.println("\ts -> save options");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: allows you to choose between adding and removing an exercise from a workout
    private void editWorkout() {
        String selection = "";
        System.out.println("Do you wish to add or remove an exercise?");

        while (! (selection.equals("add") || selection.equals("remove"))) {
            System.out.println("- add");
            System.out.println("- remove");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("add")) {
            addExercise();
        } else {
            removeExercise();
        }
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
    // EFFECTS: if the exercise is already in the list, remove it;
    //          otherwise, do not change the workout
    private void removeExercise() {
        Workout selected = selectWorkout();
        System.out.println("Enter name of exercise to remove:");
        input.nextLine();
        String exercise = input.nextLine();

        // find the position of the exercise then remove it afterwards
        int removedExercisePos = 1000;
        for (Exercise e : selected.getWorkout()) {
            if (e.getName().equals(exercise)) {
                removedExercisePos = selected.getWorkout().indexOf(e);
            } else {
                System.out.println("Given exercise is not in this workout.");
            }
        }
        selected.getWorkout().remove(removedExercisePos);

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
        System.out.println("Enter the name of your meal:");
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
            System.out.println("- workouts");
            System.out.println("- fitness goals");
            System.out.println("- meals");
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
            System.out.println("- push");
            System.out.println("- pull");
            System.out.println("- legs");
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

    // EFFECTS: prompts the user to select the file to load and loads it
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void load() {
        String selection = "";
        System.out.println("What would you like to load from file?");
        while (!(selection.equals("push workout") || selection.equals("pull workout")
                || selection.equals("legs workout") || selection.equals("fitness goals")
                || selection.equals("meals"))) {
            System.out.println("- push workout");
            System.out.println("- pull workout");
            System.out.println("- legs workout");
            System.out.println("- fitness goals");
            System.out.println("- meals");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("push workout")) {
            loadPushWorkout();
        } else if (selection.equals("pull workout")) {
            loadPullWorkout();
        } else if (selection.equals("legs workout")) {
            loadLegsWorkout();
        } else if (selection.equals("fitness goals")) {
            loadFitnessGoals();
        } else {
            loadDailyMeals();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads push workout from file
    public void loadPushWorkout() {
        try {
            push = jsonPushReader.readPushWorkout();
            System.out.println("Loaded " + push.getWorkoutTitle() + " from " + PUSH_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + PUSH_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads pull workout from file
    public void loadPullWorkout() {
        try {
            pull = jsonPullReader.readPullWorkout();
            System.out.println("Loaded " + pull.getWorkoutTitle() + " from " + PULL_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + PULL_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads legs workout from file
    public void loadLegsWorkout() {
        try {
            legs = jsonLegsReader.readLegsWorkout();
            System.out.println("Loaded " + legs.getWorkoutTitle() + " from " + LEGS_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + LEGS_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads fitness goals from file
    public void loadFitnessGoals() {
        try {
            fitnessGoals = jsonGoalsReader.readListOfFitnessGoals();
            System.out.println("Loaded fitness goals from " + GOALS_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + GOALS_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads meals from file
    public void loadDailyMeals() {
        try {
            meals = jsonMealsReader.readMeals();
            System.out.println("Loaded meals from " + MEALS_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + MEALS_STORE);
        }
    }

    // EFFECTS: prompts the user to select the data to save and saves it to file
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void save() {
        String selection = "";
        System.out.println("What would you like to save to file?");
        while (!(selection.equals("push workout") || selection.equals("pull workout")
                || selection.equals("legs workout") || selection.equals("fitness goals")
                || selection.equals("meals"))) {
            System.out.println("- push workout");
            System.out.println("- pull workout");
            System.out.println("- legs workout");
            System.out.println("- fitness goals");
            System.out.println("- meals");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("push workout")) {
            savePushWorkout();
        } else if (selection.equals("pull workout")) {
            savePullWorkout();
        } else if (selection.equals("legs workout")) {
            saveLegsWorkout();
        } else if (selection.equals("fitness goals")) {
            saveFitnessGoals();
        } else {
            saveDailyMeals();
        }
    }

    // EFFECTS: saves push workout to file
    public void savePushWorkout() {
        try {
            jsonPushWriter.open();
            jsonPushWriter.writePushWorkout(push);
            jsonPushWriter.close();
            System.out.println("Saved " + push.getWorkoutTitle() + " to " + PUSH_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + PUSH_STORE);
        }
    }

    // EFFECTS: saves pull workout to file
    public void savePullWorkout() {
        try {
            jsonPullWriter.open();
            jsonPullWriter.writePullWorkout(pull);
            jsonPullWriter.close();
            System.out.println("Saved " + pull.getWorkoutTitle() + " to " + PULL_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + PULL_STORE);
        }
    }

    // EFFECTS: saves legs workout to file
    public void saveLegsWorkout() {
        try {
            jsonLegsWriter.open();
            jsonLegsWriter.writeLegsWorkout(legs);
            jsonLegsWriter.close();
            System.out.println("Saved " + legs.getWorkoutTitle() + " to " + LEGS_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + LEGS_STORE);
        }
    }

    // EFFECTS: saves fitness goals to file
    public void saveFitnessGoals() {
        try {
            jsonGoalsWriter.open();
            jsonGoalsWriter.writeFitnessGoals(fitnessGoals);
            jsonGoalsWriter.close();
            System.out.println("Saved fitness goals to " + GOALS_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + GOALS_STORE);
        }
    }

    // EFFECTS: saves meals to file
    public void saveDailyMeals() {
        try {
            jsonMealsWriter.open();
            jsonMealsWriter.writeMeals(meals);
            jsonMealsWriter.close();
            System.out.println("Saved meals to " + MEALS_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + MEALS_STORE);
        }
    }
}
