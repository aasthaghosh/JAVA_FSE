package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    // Autowired setter method for dependency injection
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void manageBooks() {
        System.out.println("BookService: Managing books using annotated components...");
        if (bookRepository != null) {
            bookRepository.execute();
        } else {
            System.out.println("BookService error: BookRepository annotation injection failed!");
        }
    }
}
