package com.example.OrderDishes.model;

public enum Dish {
    PIZZA("Pizza", 10.99),
    PASTA("Pasta", 7.99),
    BURGER("Burger", 8.49),
    SALAD("Salad", 5.99);

    private final String name;
    private final double price;

    Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}