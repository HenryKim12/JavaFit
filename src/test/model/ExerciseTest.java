package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {

    private Exercise e1, e2, e3, e4, e5, e6;

    @BeforeEach
    private void setUp() {
        e1 = new Exercise("Bench Press", "chest", 4, 6);
        e2 = new Exercise("Lat Pulldown", "back", 4, 12);
        e3 = new Exercise("Bicep Curl", "arms", 4, 10);
        e4 = new Exercise("Lateral Raises", "shoulders", 3, 12);
        e5 = new Exercise("Barbell Squat", "legs", 4, 5);
        e6 = new Exercise("Bench Press", "arms", 1, 1);
    }

    @Test
    public void testExercise() {
        assertEquals("Bench Press", e1.getName());
        assertEquals("chest", e1.getMuscleGroup());
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

    @Test
    public void testSetSets() {
        assertEquals(4, e1.getSets());
        e1.setSets(6);
        assertEquals(6, e1.getSets());
    }

    @Test
    public void testSetReps() {
        assertEquals(5, e5.getReps());
        e5.setReps(3);
        assertEquals(3, e5.getReps());
    }

    @Test
    public void testEquals() {
        assertTrue(e1.equals(e6));
        assertFalse(e1.equals("hello"));
    }

    @Test
    public void testToJson() {
        assertEquals(null, e1.toJson());
    }
}
