package ru.geekbrains.katebatasheva.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.geekbrains.katebatasheva.model.Customer;

import javax.persistence.EntityManagerFactory;

@Component
public class CustomerRepository {

    private SessionFactory factory;

    @Autowired
    public CustomerRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.factory = factory.unwrap(SessionFactory.class);
    }

    public void createCustomer(String title) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = new Customer(title);
            session.save(customer);
            session.getTransaction().commit();
        }
    }

    public void readAndPrintCustomer(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            System.out.println(customer);
            session.getTransaction().commit();
        }
    }

    public void updateCustomer(long id, String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            customer.setName(name);
            session.getTransaction().commit();
        }
    }

    public void deleteCustomer(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.delete(customer);
            session.getTransaction().commit();
        }
    }

}
