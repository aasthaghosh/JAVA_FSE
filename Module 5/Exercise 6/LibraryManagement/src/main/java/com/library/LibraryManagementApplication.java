package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        // Load the Spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve wired BookService bean
        BookService bookService = context.getBean(BookService.class);

        // Test configuration
        bookService.manageBooks();

        System.out.println("Exercise 6 Annotation-based Configuration tested successfully!");
    }
}
