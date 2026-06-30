package com.advanced;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    private static final StringBuilder orderSequence = new StringBuilder();

    @Test
    @Order(1)
    public void testFirst() {
        orderSequence.setLength(0);
        orderSequence.append("A");
        System.out.println("Executed: testFirst (Order 1)");
        assertEquals("A", orderSequence.toString());
    }

    @Test
    @Order(2)
    public void testSecond() {
        orderSequence.append("B");
        System.out.println("Executed: testSecond (Order 2)");
        assertEquals("AB", orderSequence.toString());
    }

    @Test
    @Order(3)
    public void testThird() {
        orderSequence.append("C");
        System.out.println("Executed: testThird (Order 3)");
        assertEquals("ABC", orderSequence.toString());
    }
}
