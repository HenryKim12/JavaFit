package model;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.MuscleGroup.*;
import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {

    private Workout testWorkout;
    private Exercise e1, e2, e3;

    @BeforeEach
    public void setUp() {
        testWorkout = new Workout("Chest Day");
        e1 = new Exercise("Bench Press", CHEST, 4, 8);
        e2 = new Exercise("Chest Fly", CHEST, 3, 10);
        e3 = new Exercise("Bicep Curl", ARMS, 4, 12);
    }

    @Test
    public void testEmptyWorkout() {
        assertEquals(0, testWorkout.getWorkout().size());
    }

    @Test
    public void testAddingExercise() {
        testWorkout.addExercise(e1);
        assertEquals(1, testWorkout.getWorkout().size());
        assertTrue(testWorkout.getWorkout().contains(e1));
    }

    @Test
    public void testAddingExerciseAlreadyInWorkout() {
        testWorkout.addExercise(e1);
        assertEquals(1, testWorkout.getWorkout().size());
        assertTrue(testWorkout.getWorkout().contains(e1));
        assertEquals(4, testWorkout.getExercise(testWorkout.getWorkout(), 0).getSets());
        assertEquals(8, testWorkout.getExercise(testWorkout.getWorkout(), 0).getReps());
        testWorkout.addExercise(e1);
        assertEquals(1, testWorkout.getWorkout().size());
        assertTrue(testWorkout.getWorkout().contains(e1));
        assertEquals(8, testWorkout.getExercise(testWorkout.getWorkout(), 0).getSets());
        assertEquals(16, testWorkout.getExercise(testWorkout.getWorkout(), 0).getReps());
    }

    @Test
    public void testRearrangeWorkout() {
        testWorkout.addExercise(e1);
        testWorkout.addExercise(e2);
        testWorkout.addExercise(e3);
        assertEquals(0, testWorkout.getWorkout().indexOf(e1));
        assertEquals(1, testWorkout.getWorkout().indexOf(e2));
        assertEquals(2, testWorkout.getWorkout().indexOf(e3));
        testWorkout.rearrangeWorkout(e3, 1);
        assertEquals(0, testWorkout.getWorkout().indexOf(e1));
        assertEquals(1, testWorkout.getWorkout().indexOf(e3));
        assertEquals(2, testWorkout.getWorkout().indexOf(e2));
    }
}
