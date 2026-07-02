package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve and test constructor-injected service
        BookService constructorService = (BookService) context.getBean("bookServiceConstructor");
        constructorService.manageBooks();

        // Retrieve and test setter-injected service
        BookService setterService = (BookService) context.getBean("bookServiceSetter");
        setterService.manageBooks();

        System.out.println("Exercise 7 Constructor and Setter injection tested successfully!");
    }
}
