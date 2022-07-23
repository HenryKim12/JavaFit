package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DailyMealsTest {

    private DailyMeals testAllMeals;
    private Meal m1;
    private Meal m2;

    @BeforeEach
    private void setUp() {
        testAllMeals = new DailyMeals();
        m1 = new Meal("Chicken", 200, 1, 1, 15);
        m2 = new Meal("Milk", 80, 0, 2, 6);
    }

    @Test
    public void testAllMealsEmpty() {
        assertEquals(0, testAllMeals.getDailyMeals().size());
    }

    @Test
    public void testAddingOneMeal() {
        assertTrue(testAllMeals.addMeal(m1));
        assertEquals(1, testAllMeals.getDailyMeals().size());
        assertTrue(testAllMeals.getDailyMeals().contains(m1));
    }

    @Test
    public void testAddingTwoMeals() {
        assertTrue(testAllMeals.addMeal(m1));
        assertTrue(testAllMeals.addMeal(m2));
        assertEquals(2, testAllMeals.getDailyMeals().size());
        assertTrue(testAllMeals.getDailyMeals().contains(m1));
        assertTrue(testAllMeals.getDailyMeals().contains(m2));
    }
}
