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
    void testWriterEmptyAccount() {
        try {
            Workout workout = new Workout("Push Day");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccount.json");
            writer.open();
            writer.writeWorkout(workout);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccount.json");
            workout = reader.readWorkout();
            assertEquals("Push Day", workout.getWorkoutTitle());
            assertEquals(0, workout.getWorkout().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
        try {
            ListOfFitnessGoals fitnessGoals = new ListOfFitnessGoals();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccount.json");
            writer.open();
            writer.writeFitnessGoals(fitnessGoals);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccount.json");
            fitnessGoals = reader.readListOfFitnessGoals();
            assertEquals(0, fitnessGoals.getAllFitnessGoals().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
        try {
            DailyMeals eatenToday = new DailyMeals();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccount.json");
            writer.open();
            writer.writeMeals(eatenToday);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccount.json");
            eatenToday = reader.readMeals();
            assertEquals(0, eatenToday.getDailyMeals().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAccount() {
        try {
            Workout workout = new Workout("Push Day");
            workout.addExercise(new Exercise("bench", "chest", 4, 8));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccount.json");
            writer.open();
            writer.writeWorkout(workout);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccount.json");
            workout = reader.readWorkout();
            assertEquals("Push Day", workout.getWorkoutTitle());
            ArrayList<Exercise> exercises = workout.getWorkout();
            assertEquals(1, exercises.size());
            checkExercise("bench", 4, 8, exercises.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

        try {
            ListOfFitnessGoals fitnessGoals = new ListOfFitnessGoals();
            fitnessGoals.addFitnessGoal(new FitnessGoal("I want to lose 10 pounds in 6 months"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccount.json");
            writer.open();
            writer.writeFitnessGoals(fitnessGoals);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccount.json");
            fitnessGoals = reader.readListOfFitnessGoals();
            ArrayList<FitnessGoal> goals = fitnessGoals.getAllFitnessGoals();
            assertEquals(1, goals.size());
            checkFitnessGoal("I want to lose 10 pounds in 6 months", goals.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

        try {
            DailyMeals eatenToday = new DailyMeals();
            eatenToday.addMeal(new Meal("chicken and rice", 500, 5, 2, 12));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccount.json");
            writer.open();
            writer.writeMeals(eatenToday);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccount.json");
            eatenToday = reader.readMeals();
            ArrayList<Meal> meals = eatenToday.getDailyMeals();
            assertEquals(1, meals.size());
            checkMeal("chicken and rice", 500, 5, 2, 12, meals.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
