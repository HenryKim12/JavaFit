package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealTest {

    private Meal m1, m2, m3;

    @BeforeEach
    public void setUp() {
        m1 = new Meal("Chicken and Rice", 550, 45, 1, 20);
        m2 = new Meal("Diet Pepsi", 0, 0, 0, 0);
        m3 = new Meal("Mac and Cheese", 250, 45, 5, 8);
    }

    @Test
    public void testMeal() {
        assertEquals("Chicken and Rice", m1.getName());
        assertEquals(0, m2.getCalories());
        assertEquals(45, m3.getCarbohydrates());
        assertEquals(1, m1.getFats());
        assertEquals(8, m3.getProtein());
    }

    @Test
    public void testToString() {
        assertEquals("Diet Pepsi | 0cal | 0carbs | 0fats | 0protein", m2.toString());
    }
}
