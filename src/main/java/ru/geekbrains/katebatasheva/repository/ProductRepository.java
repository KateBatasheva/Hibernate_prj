package ru.geekbrains.katebatasheva.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.geekbrains.katebatasheva.model.Products;

import javax.persistence.EntityManagerFactory;

@Component
public class ProductRepository {

    private SessionFactory factory;

    @Autowired
    public ProductRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.factory = factory.unwrap(SessionFactory.class);
    }

    public void createProduct(String title, int cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products product = new Products(title, cost);
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public void readAndPrintProduct(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products product = session.get(Products.class, id);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public void updateProduct(long id, int cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products products = session.get(Products.class, id);
            products.setCost(cost);
            session.getTransaction().commit();
        }
    }

    public void deleteProduct(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products products = session.get(Products.class, id);
            session.delete(products);
            session.getTransaction().commit();
        }
    }

}
