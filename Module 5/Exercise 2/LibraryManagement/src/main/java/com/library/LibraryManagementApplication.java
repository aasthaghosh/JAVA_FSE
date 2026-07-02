package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        // Load the Spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the wired BookService bean
        BookService bookService = (BookService) context.getBean("bookService");

        // Call method to test dependency injection
        bookService.manageBooks();

        System.out.println("Exercise 2 Dependency Injection tested successfully!");
    }
}
