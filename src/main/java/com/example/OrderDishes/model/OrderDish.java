package com.example.OrderDishes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderDish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING) // make dish name a string type when save in database
    private Dish dish;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false) // make sure all the dish in the same order
    @JsonBackReference
    private Order order;

    public OrderDish() {}

    public OrderDish(Dish dish, int quantity, Order order) {
        this.dish = dish;
        this.quantity = quantity;
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDish [dish=" + dish.getName() + ", quantity=" + quantity + "]";
    }
}