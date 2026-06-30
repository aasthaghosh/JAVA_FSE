package com.advanced;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class PerformanceTesterTest {

    @Test
    public void testPerformTaskTimeoutProgrammatic() {
        PerformanceTester tester = new PerformanceTester();

        // Check that a 50ms task finishes well within a 500ms limit
        assertTimeout(Duration.ofMillis(500), () -> {
            tester.performTask(50);
        });
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    public void testPerformTaskTimeoutAnnotation() {
        PerformanceTester tester = new PerformanceTester();
        // This test will fail if it exceeds 500ms
        tester.performTask(50);
    }
}
