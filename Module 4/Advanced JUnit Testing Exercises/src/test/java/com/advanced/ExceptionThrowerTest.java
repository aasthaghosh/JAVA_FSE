package com.advanced;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionThrowerTest {

    @Test
    public void testIllegalArgumentException() {
        ExceptionThrower thrower = new ExceptionThrower();

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> thrower.throwException("illegal")
        );

        assertEquals("Illegal argument exception thrown", exception.getMessage());
    }

    @Test
    public void testNullPointerException() {
        ExceptionThrower thrower = new ExceptionThrower();

        NullPointerException exception = assertThrows(
            NullPointerException.class,
            () -> thrower.throwException("nullpointer")
        );

        assertEquals("Null pointer exception thrown", exception.getMessage());
    }

    @Test
    public void testGenericRuntimeException() {
        ExceptionThrower thrower = new ExceptionThrower();

        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> thrower.throwException("other")
        );

        assertEquals("Generic runtime exception thrown", exception.getMessage());
    }
}
