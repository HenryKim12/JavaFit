package persistence;

import model.Exercise;
import model.FitnessGoal;
import model.Meal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExercise(String name, int sets, int reps, Exercise exercise) {
        assertEquals(name, exercise.getName());
        assertEquals(sets, exercise.getSets());
        assertEquals(reps, exercise.getReps());
    }
    protected void checkFitnessGoal(String goal, FitnessGoal fitnessGoal) {
        assertEquals(goal, fitnessGoal.getGoal());
    }

    protected void checkMeal(String name, int calories, int carbs, int fats, int protein, Meal meal) {
        assertEquals(name, meal.getName());
        assertEquals(calories, meal.getCalories());
        assertEquals(carbs, meal.getCarbohydrates());
        assertEquals(fats, meal.getFats());
        assertEquals(protein, meal.getProtein());
    }
}
