package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfFitnessGoalsTest {

    private ListOfFitnessGoals testFitnessList;
    private FitnessGoal fg1, fg2;

    @BeforeEach
    public void setUp() {
        testFitnessList = new ListOfFitnessGoals();
        fg1 = new FitnessGoal("I want to go to the gym 5x a week.");
        fg2 = new FitnessGoal("I want to eat less than 2000 calories a day.");
    }

    @Test
    public void testEmptyListOfFitnessGoals() {
        assertEquals(0, testFitnessList.getAllFitnessGoals().size());
    }

    @Test
    public void testAddingOneFitnessGoal() {
        assertTrue(testFitnessList.addFitnessGoal(fg1));
        assertEquals(1, testFitnessList.getAllFitnessGoals().size());
        assertTrue(testFitnessList.getAllFitnessGoals().contains(fg1));
    }

    @Test
    public void testAddingFitnessGoalAlreadyInList() {
        assertTrue(testFitnessList.addFitnessGoal(fg1));
        assertEquals(1, testFitnessList.getAllFitnessGoals().size());
        assertTrue(testFitnessList.getAllFitnessGoals().contains(fg1));
        assertFalse(testFitnessList.addFitnessGoal(fg1));
        assertEquals(1, testFitnessList.getAllFitnessGoals().size());
        assertTrue(testFitnessList.getAllFitnessGoals().contains(fg1));
    }

    @Test
    public void testAddingDifferentFitnessGoals() {
        assertTrue(testFitnessList.addFitnessGoal(fg1));
        assertEquals(1, testFitnessList.getAllFitnessGoals().size());
        assertTrue(testFitnessList.addFitnessGoal(fg2));
        assertEquals(2, testFitnessList.getAllFitnessGoals().size());
        assertTrue(testFitnessList.getAllFitnessGoals().contains(fg1));
        assertTrue(testFitnessList.getAllFitnessGoals().contains(fg2));
    }

    @Test
    public void testRemovingFitnessGoalFromList() {
        assertTrue(testFitnessList.addFitnessGoal(fg1));
        assertEquals(1, testFitnessList.getAllFitnessGoals().size());
        assertTrue(testFitnessList.getAllFitnessGoals().contains(fg1));
        assertTrue(testFitnessList.removeFitnessGoal(fg1));
        assertEquals(0, testFitnessList.getAllFitnessGoals().size());
        assertFalse(testFitnessList.getAllFitnessGoals().contains(fg1));
    }

    @Test
    public void testRemovingFitnessGoalNotInList() {
        assertTrue(testFitnessList.addFitnessGoal(fg1));
        assertEquals(1, testFitnessList.getAllFitnessGoals().size());
        assertTrue(testFitnessList.getAllFitnessGoals().contains(fg1));
        assertFalse(testFitnessList.removeFitnessGoal(fg2));
        assertEquals(1, testFitnessList.getAllFitnessGoals().size());
        assertTrue(testFitnessList.getAllFitnessGoals().contains(fg1));
    }
}
