package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    // Setter method for dependency injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void manageBooks() {
        System.out.println("BookService: Delegating library management tasks...");
        if (bookRepository != null) {
            bookRepository.execute();
        } else {
            System.out.println("BookService error: BookRepository is not injected!");
        }
    }
}
