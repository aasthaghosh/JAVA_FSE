package com.cognizant.springlearn.service;

import com.cognizant.springlearn.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
