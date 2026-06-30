package com.example.springtesting.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    // Exercise 1: Basic Unit Test for a Service Method
    @Test
    public void testAddBasic() {
        int result = calculatorService.add(5, 3);
        assertEquals(8, result);
    }

    // Exercise 9: Parameterized Test with JUnit
    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",
        "10, 20, 30",
        "-5, 5, 0",
        "0, 0, 0",
        "-10, -5, -15"
    })
    public void testAddParameterized(int a, int b, int expected) {
        int result = calculatorService.add(a, b);
        assertEquals(expected, result);
    }
}
