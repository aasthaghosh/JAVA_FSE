package com.advanced;

public class PerformanceTester {

    public void performTask(long durationMillis) {
        try {
            Thread.sleep(durationMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task was interrupted", e);
        }
    }
}
