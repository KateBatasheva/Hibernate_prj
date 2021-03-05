package ru.geekbrains.katebatasheva;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.geekbrains.katebatasheva.service.Connection;
import ru.geekbrains.katebatasheva.service.CustomerService;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@SpringBootApplication
public class HiberApplication {


    public static void main(String[] args) {
        SpringApplication.run(HiberApplication.class, args);


    }
}
