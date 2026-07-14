package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Country;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @GetMapping("/country")
    public Country getCountry() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        return context.getBean("country", Country.class);
    }
}
