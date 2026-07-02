package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    // Setter method for injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void manageBooks() {
        System.out.println("BookService: Managing centralized library flow...");
        if (bookRepository != null) {
            bookRepository.execute();
        } else {
            System.out.println("BookService: BookRepository dependency NOT set!");
        }
    }
}
