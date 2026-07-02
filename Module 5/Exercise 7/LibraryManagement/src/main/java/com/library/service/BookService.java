package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;
    private String injectionType;

    // Default constructor (used for setter injection)
    public BookService() {
        this.injectionType = "Setter Injection / Default Constructor";
    }

    // Parameterized constructor for constructor injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.injectionType = "Constructor Injection";
    }

    // Setter method for setter injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void manageBooks() {
        System.out.println("BookService (Mode: " + injectionType + "): Managing library books...");
        if (bookRepository != null) {
            bookRepository.execute();
        } else {
            System.out.println("BookRepository dependency is null!");
        }
    }
}
