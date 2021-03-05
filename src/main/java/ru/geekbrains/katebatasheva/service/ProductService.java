package ru.geekbrains.katebatasheva.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.geekbrains.katebatasheva.model.Products;
import ru.geekbrains.katebatasheva.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

    @Autowired
    private ProductRepository repository;

    private SessionFactory factory;

    public void createProduct(String title, int cost) {
        repository.createProduct(title, cost);
    }

    public void readAndPrintProduct(long id) {
        repository.readAndPrintProduct(id);
    }

    public void updateProduct(long id, int cost) {
        repository.updateProduct(id, cost);
    }

    public void deleteProduct(long id) {
        repository.deleteProduct(id);
    }

    public List<String> getAllCustomersByProductId (long id){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<String> customers = Arrays.stream(session.get(Products.class,id).getCustomers().toArray()).map(Object::toString).collect(Collectors.toList());
            System.out.println(customers);
            session.getTransaction().commit();
            return customers;
        }
    }
    public List<String> getAllProduct (){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            int res =  session.createQuery("SELECT * FROM products;").executeUpdate();
            List<String> products = new ArrayList<>();
            for (long i = 1; i <= res; i++) {
               products.add(session.get(Products.class,i).toString());
            }
            System.out.println(products);
            session.getTransaction().commit();
            return products;
        }
    }
}
