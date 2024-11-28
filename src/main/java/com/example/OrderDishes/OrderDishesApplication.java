package com.example.OrderDishes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderDishesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderDishesApplication.class, args);
	}

}
