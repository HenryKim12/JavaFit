package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Workout workout = new Workout("Push Day");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPushWorkout() {
        try {
            Workout workout = new Workout("Push Day");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPushWorkout.json");
            writer.open();
            writer.writePushWorkout(workout);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPushWorkout.json");
            workout = reader.readPushWorkout();
            assertEquals("Push Day", workout.getWorkoutTitle());
            assertEquals(0, workout.getWorkout().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyPullWorkout() {
        try {
            Workout workout = new Workout("Pull Day");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPullWorkout.json");
            writer.open();
            writer.writePullWorkout(workout);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPullWorkout.json");
            workout = reader.readPullWorkout();
            assertEquals("Pull Day", workout.getWorkoutTitle());
            assertEquals(0, workout.getWorkout().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyLegsWorkout() {
        try {
            Workout workout = new Workout("Leg Day");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLegsWorkout.json");
            writer.open();
            writer.writeLegsWorkout(workout);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLegsWorkout.json");
            workout = reader.readLegsWorkout();
            assertEquals("Leg Day", workout.getWorkoutTitle());
            assertEquals(0, workout.getWorkout().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyFitnessGoals() {
        try {
            ListOfFitnessGoals fitnessGoals = new ListOfFitnessGoals();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFitnessGoals.json");
            writer.open();
            writer.writeFitnessGoals(fitnessGoals);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFitnessGoals.json");
            fitnessGoals = reader.readListOfFitnessGoals();
            assertEquals(0, fitnessGoals.getAllFitnessGoals().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyMeals() {
        try {
            DailyMeals eatenToday = new DailyMeals();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMeals.json");
            writer.open();
            writer.writeMeals(eatenToday);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMeals.json");
            eatenToday = reader.readMeals();
            assertEquals(0, eatenToday.getDailyMeals().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPushWorkout() {
        try {
            Workout workout = new Workout("Push Day");
            workout.addExercise(new Exercise("bench", "chest", 4, 8));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPushWorkout.json");
            writer.open();
            writer.writePushWorkout(workout);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPushWorkout.json");
            workout = reader.readPushWorkout();
            assertEquals("Push Day", workout.getWorkoutTitle());
            ArrayList<Exercise> exercises = workout.getWorkout();
            assertEquals(1, exercises.size());
            checkExercise("bench", 4, 8, exercises.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPullWorkout() {
        try {
            Workout workout = new Workout("Pull Day");
            workout.addExercise(new Exercise("pulldown", "back", 3, 10));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPullWorkout.json");
            writer.open();
            writer.writePullWorkout(workout);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPullWorkout.json");
            workout = reader.readPullWorkout();
            assertEquals("Pull Day", workout.getWorkoutTitle());
            ArrayList<Exercise> exercises = workout.getWorkout();
            assertEquals(1, exercises.size());
            checkExercise("pulldown", 3, 10, exercises.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLegsWorkout() {
        try {
            Workout workout = new Workout("Leg Day");
            workout.addExercise(new Exercise("squats", "legs", 3, 10));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLegsWorkout.json");
            writer.open();
            writer.writeLegsWorkout(workout);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLegsWorkout.json");
            workout = reader.readLegsWorkout();
            assertEquals("Leg Day", workout.getWorkoutTitle());
            ArrayList<Exercise> exercises = workout.getWorkout();
            assertEquals(1, exercises.size());
            checkExercise("squats", 3, 10, exercises.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFitnessGoals() {
        try {
            ListOfFitnessGoals fitnessGoals = new ListOfFitnessGoals();
            fitnessGoals.addFitnessGoal(new FitnessGoal("I want to lose 10 pounds in 6 months"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFitnessGoals.json");
            writer.open();
            writer.writeFitnessGoals(fitnessGoals);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFitnessGoals.json");
            fitnessGoals = reader.readListOfFitnessGoals();
            ArrayList<FitnessGoal> goals = fitnessGoals.getAllFitnessGoals();
            assertEquals(1, goals.size());
            checkFitnessGoal("I want to lose 10 pounds in 6 months", goals.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMeals() {
        try {
            DailyMeals eatenToday = new DailyMeals();
            eatenToday.addMeal(new Meal("chicken and rice", 500, 5, 2, 12));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMeals.json");
            writer.open();
            writer.writeMeals(eatenToday);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMeals.json");
            eatenToday = reader.readMeals();
            ArrayList<Meal> meals = eatenToday.getDailyMeals();
            assertEquals(1, meals.size());
            checkMeal("chicken and rice", 500, 5, 2, 12, meals.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
