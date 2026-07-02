package com.library;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(BookRepository repository) {
        return args -> {
            repository.save(new Book("Spring in Action", "Craig Walls"));
            repository.save(new Book("Clean Code", "Robert C. Martin"));
            repository.save(new Book("Effective Java", "Joshua Bloch"));
            System.out.println("Exercise 9 Spring Boot sample books saved successfully!");
        };
    }
}
