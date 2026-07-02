package com.library.repository;

public class BookRepository {
    public void execute() {
        System.out.println("BookRepository: Accessing database to fetch books...");
        try {
            Thread.sleep(150); // Simulate database delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
