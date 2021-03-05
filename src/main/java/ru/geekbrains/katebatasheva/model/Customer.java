package ru.geekbrains.katebatasheva.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "id_customer"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    List<Products> products;

    public Customer(String name) {
        this.name = name;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return String.format("Customer [id = %d, name = %s", id, name);
    }
}
