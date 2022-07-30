package persistence;

import com.sun.xml.internal.ws.policy.spi.PolicyAssertionValidator;
import org.junit.jupiter.api.Test;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fileDNE.json");
        try {
            Workout workout = reader.readWorkout();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccount() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAccount.json");
        try {
            Workout workout = reader.readWorkout();
            assertEquals("Push Day", workout.getWorkoutTitle());
            assertEquals(0, workout.getWorkout().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
        try {
            Workout workout = reader.readWorkout();
            assertEquals("Pull Day", workout.getWorkoutTitle());
            assertEquals(0, workout.getWorkout().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
        try {
            Workout workout = reader.readWorkout();
            assertEquals("Leg Day", workout.getWorkoutTitle());
            assertEquals(0, workout.getWorkout().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
        try {
            ListOfFitnessGoals fitnessGoals = reader.readListOfFitnessGoals();
            assertEquals(0, fitnessGoals.getAllFitnessGoals().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        try {
            DailyMeals eatenToday = reader.readMeals();
            assertEquals(0, eatenToday.getDailyMeals().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAccount() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAccount.json");
        try {
            Workout workout = reader.readWorkout();
            assertEquals("Push Day", workout.getWorkoutTitle());
            ArrayList<Exercise> exercises = workout.getWorkout();
            assertEquals(3, exercises.size());
            checkExercise("bench press", 4, 8, exercises.get(0));
            checkExercise("incline dumbbell press", 4, 12, exercises.get(1));
            checkExercise("flys", 4, 10, exercises.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        try {
            Workout workout = reader.readWorkout();
            assertEquals("Pull Day", workout.getWorkoutTitle());
            ArrayList<Exercise> exercises = workout.getWorkout();
            assertEquals(1, exercises.size());
            checkExercise("lat pulldown", 4, 12, exercises.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        try {
            Workout workout = reader.readWorkout();
            assertEquals("Leg Day", workout.getWorkoutTitle());
            ArrayList<Exercise> exercises = workout.getWorkout();
            assertEquals(1, exercises.size());
            checkExercise("squats", 4, 10, exercises.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        try {
            ListOfFitnessGoals fitnessGoals = reader.readListOfFitnessGoals();
            ArrayList<FitnessGoal> allGoals = fitnessGoals.getAllFitnessGoals();
            assertEquals(2, fitnessGoals.getAllFitnessGoals().size());
            checkFitnessGoal("I want to lose 10 pounds in 6 months", fitnessGoals.getAllFitnessGoals().get(0));
            checkFitnessGoal("I want to strengthen my back", fitnessGoals.getAllFitnessGoals().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        try {
            DailyMeals eatenToday = reader.readMeals();
            ArrayList<Meal> allMeals = eatenToday.getDailyMeals();
            assertEquals(2, eatenToday.getDailyMeals().size());
            checkMeal("chicken and rice", 500, 5, 2, 12, eatenToday.getMeal(0));
            checkMeal("ice cream", 250, 0, 1, 2, eatenToday.getMeal(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
