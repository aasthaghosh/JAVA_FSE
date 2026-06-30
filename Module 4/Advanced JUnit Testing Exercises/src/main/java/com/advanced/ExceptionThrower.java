package com.advanced;

public class ExceptionThrower {

    public void throwException(String type) {
        if ("illegal".equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("Illegal argument exception thrown");
        } else if ("nullpointer".equalsIgnoreCase(type)) {
            throw new NullPointerException("Null pointer exception thrown");
        } else {
            throw new RuntimeException("Generic runtime exception thrown");
        }
    }
}
