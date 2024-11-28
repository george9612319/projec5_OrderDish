package com.example.OrderDishes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.OrderDishes.model.Dish;
import com.example.OrderDishes.model.Order;
import com.example.OrderDishes.model.OrderDish;
import com.example.OrderDishes.service.OrderService;


@Controller
public class ViewController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/")
    public String home() {
        return "index"; //
    }
	
	@GetMapping("/order")
    public String orderPage() {
        return "order"; //
    }
	
	 @PostMapping("/confirmation")
	    public String submitOrder(@RequestParam Map<String, String> formParams, Model model) {
		 
		 Order order = new Order();
		    order.setCustomerName(formParams.get("customerName"));
		    order.setOrderType(formParams.get("orderType"));
		  
		 List<OrderDish> dishes = new ArrayList<>();

	        for (Dish dish : Dish.values()) { // Iterate through all possible dish enums
	            if (formParams.containsKey(dish.name())) {
	                int quantity = Integer.parseInt(formParams.get(dish.name())); // Parse the quantity
	                if (quantity > 0) {
	                    // Create a new OrderDish object and add to the list
	                    OrderDish orderDish = new OrderDish();
	                    orderDish.setDish(dish);
	                    orderDish.setQuantity(quantity);
	                    dishes.add(orderDish);
	                }
	            }
	        }
	        
	        order.setDishes(dishes);
	        System.out.println("Order before saving: " + order);
	        Order savedOrder = orderService.saveOrder(order);
	        model.addAttribute("order", order);
	        model.addAttribute("totalPrice", savedOrder.getTotalPrice());
	        System.out.println("Saved Order from DB: " + savedOrder);

	        
	        return "confirmation";
	    }
	 
	 @GetMapping("/confirmation")
	 public String confirmationPage(@RequestParam int orderId, Model model) {
	     Optional<Order> order = orderService.getOrderById(orderId);
	     if (order.isPresent()) {
	         model.addAttribute("order", order.get());
	     }
	     return "confirmation";
	 }


}
