package de.hindenbug.dox.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sample REST application based on spring boot
 * @see <a href="https://spring.io/guides/gs/rest-service/">Building a RESTful Web Service</a>
 */
@SpringBootApplication
public class CalculatorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(CalculatorApplication.class, args);
    }
}
