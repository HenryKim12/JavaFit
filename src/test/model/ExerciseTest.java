package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.MuscleGroup.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {

    private Exercise e1, e2, e3, e4, e5;

    @BeforeEach
    private void setUp() {
        e1 = new Exercise("Bench Press", CHEST, 4, 6);
        e2 = new Exercise("Lat Pulldown", BACK, 4, 12);
        e3 = new Exercise("Bicep Curl", ARMS, 4, 10);
        e4 = new Exercise("Lateral Raises", SHOULDERS, 3, 12);
        e5 = new Exercise("Barbell Squat", LEGS, 4, 5);
    }

    @Test
    public void testExercise() {
        assertEquals("Bench Press", e1.getName());
        assertEquals(CHEST, e1.getMuscleGroup());
        assertEquals(4, e1.getSets());
        assertEquals(6, e1.getReps());
    }

    @Test
    public void testAddSet() {
        assertEquals(5, e2.addSet(1));
        assertEquals(6, e4.addSet(3));
    }

    @Test
    public void testAddRep() {
        assertEquals(6, e5.addRep(1));
        assertEquals(15, e3.addRep(5));
    }
}
