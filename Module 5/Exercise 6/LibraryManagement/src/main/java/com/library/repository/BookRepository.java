package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    public void execute() {
        System.out.println("BookRepository: Accessing annotated repository...");
    }
}
