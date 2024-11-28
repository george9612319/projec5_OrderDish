package com.example.OrderDishes.model;

public class DishDTO {
    private String dish;    
    private int quantity;   

    public DishDTO() {}

    public DishDTO(String dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
