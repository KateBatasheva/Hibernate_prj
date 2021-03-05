package ru.geekbrains.katebatasheva.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.geekbrains.katebatasheva.model.Customer;
import ru.geekbrains.katebatasheva.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerService {

//    private static final String query_all_products_by_id_customer =
//            "SELECT title FROM products p " +
//                    "JOIN 'orders' o (p.id = o.id_product) " +
//                    "WHERE o.id_customer = ?";

    private SessionFactory factory;

    @Autowired
    private CustomerRepository repository;

    public void createCustomer(String title) {
        repository.createCustomer(title);
    }

    public void readAndPrintCustomer(long id) {
        repository.readAndPrintCustomer(id);
    }

    public void updateCustomer(long id, String name) {
        repository.updateCustomer(id, name);
    }

    public void deleteCustomer(long id) {
        repository.deleteCustomer(id);
    }

    public List<String> getAllProductsByCustomerId (long id){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<String> customerProducts = Arrays.stream(session.get(Customer.class,id).getProducts().toArray()).map(Object::toString).collect(Collectors.toList());
            System.out.println(customerProducts);
            session.getTransaction().commit();
            return customerProducts;
        }
    }

    public List<String> getAllCustomer (){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            int res =  session.createQuery("SELECT * FROM customers;").executeUpdate();
            List<String> customers = new ArrayList<>();
            for (long i = 1; i <= res; i++) {
                customers.add(session.get(Customer.class,i).toString());
            }
            System.out.println(customers);
            session.getTransaction().commit();
            return customers;
        }
    }

}
