package com.exercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        // Arrange phase for setup: initialize the test fixture
        calculator = new Calculator();
        System.out.println("Setup completed: Calculator instance created.");
    }

    @After
    public void tearDown() {
        // Teardown phase: cleanup after each test
        calculator = null;
        System.out.println("Teardown completed: Calculator instance cleaned up.");
    }

    @Test
    public void testAdd() {
        // Arrange
        int a = 10;
        int b = 5;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(15, result);
    }

    @Test
    public void testSubtract() {
        // Arrange
        int a = 10;
        int b = 5;

        // Act
        int result = calculator.subtract(a, b);

        // Assert
        assertEquals(5, result);
    }

    @Test
    public void testMultiply() {
        // Arrange
        int a = 10;
        int b = 5;

        // Act
        int result = calculator.multiply(a, b);

        // Assert
        assertEquals(50, result);
    }

    @Test
    public void testDivide() {
        // Arrange
        int a = 10;
        int b = 5;

        // Act
        double result = calculator.divide(a, b);

        // Assert
        assertEquals(2.0, result, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZeroThrowsException() {
        // Arrange
        int a = 10;
        int b = 0;

        // Act & Assert (Assert is verified via expected annotation property)
        calculator.divide(a, b);
    }
}
