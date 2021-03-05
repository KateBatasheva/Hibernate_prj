package ru.geekbrains.katebatasheva.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.geekbrains.katebatasheva.model.Customer;
import ru.geekbrains.katebatasheva.model.Orders;
import ru.geekbrains.katebatasheva.model.Products;

import javax.persistence.EntityManagerFactory;

@Component
public class OrdersRepository {

    private SessionFactory factory;

    @Autowired
    public OrdersRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.factory = factory.unwrap(SessionFactory.class);
    }


    public void buyProduct (Products products, Customer customer){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Orders order = new Orders(customer.getId(), products.getId(), products.getCost());
            session.save(order);
            session.getTransaction().commit();
        }
    }

}
