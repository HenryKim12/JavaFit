package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FitnessGoalTest {

    private FitnessGoal fg1, fg2;

    @BeforeEach
    public void setUp() {
        fg1 = new FitnessGoal("I want to lose 12lbs in the next 6 months.");
        fg2 = new FitnessGoal("I want to fit into my new jeans.");
    }

    @Test
    public void testFitnessGoal() {
        assertEquals("I want to lose 12lbs in the next 6 months.", fg1.getGoal());
        assertEquals("I want to fit into my new jeans.", fg2.getGoal());
    }

    @Test
    public void testToString() {
        assertEquals("I want to fit into my new jeans.", fg2.toString());
    }
}
