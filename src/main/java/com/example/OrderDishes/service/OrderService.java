package com.example.OrderDishes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderDishes.dao.OrderRepository;
import com.example.OrderDishes.model.Order;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrderById(int orderId) {
        orderRepository.deleteById(orderId);
    }

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
