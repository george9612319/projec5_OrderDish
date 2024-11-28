package com.example.OrderDishes.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.OrderDishes.model.Order;

public interface OrderRepository extends CrudRepository<Order,Integer>{
	
}
