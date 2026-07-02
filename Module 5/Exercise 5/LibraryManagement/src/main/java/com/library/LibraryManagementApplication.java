package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        // Load the Spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve wired BookService bean
        BookService bookService = (BookService) context.getBean("bookService");

        // Test configuration
        bookService.manageBooks();

        System.out.println("Exercise 5 Spring IoC Container tested successfully!");
    }
}
