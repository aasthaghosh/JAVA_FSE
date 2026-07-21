package com.cognizant.springlearn;

import com.cognizant.springlearn.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringLearnApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringLearnApplication.class, args);
        BookService bookService = context.getBean(BookService.class);
        bookService.getBooks();
    }
}
