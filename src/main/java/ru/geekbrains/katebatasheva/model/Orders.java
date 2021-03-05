package ru.geekbrains.katebatasheva.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_customer")
    private Long id_customer;


    @Column(name = "id_product")
    private Long id_product;

    @Column(name = "cost")
    private int cost;

    public Orders() {
    }

    public Orders(Long id_customer, Long id_product, int cost) {
        this.id_customer = id_customer;
        this.id_product = id_product;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return  String.format("Product [id = %d, price = %d] bought by Customer [id = %d], order# %d", id_product, cost, id_customer, id);
    }
}
