package com.example.OrderDishes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.OrderDishes.feignClient.OrderFeignClient;
import com.example.OrderDishes.model.DishDTO;
import com.example.OrderDishes.model.Order;
import com.example.OrderDishes.model.OrderDish;
import com.example.OrderDishes.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderFeignClient orderFeignClient;
    
    @GetMapping
    public ResponseEntity<Iterable<Order>> getAllOrders() {
        Iterable<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
        Optional<Order> orderOptional = orderService.getOrderById(orderId);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public RedirectView saveOrder(@RequestBody Order order) {
    	
    	List<OrderDish> orderDishes = order.getDishes();
        List<DishDTO> dishDTOs = new ArrayList<>();

        for (OrderDish dish : orderDishes) {
            DishDTO dishDTO = new DishDTO(dish.getDish().toString(), dish.getQuantity());
            dishDTOs.add(dishDTO);
        }  	// transfer order to OrderDTO
        System.out.println("DishDTOs sent to CalculateServer: " + dishDTOs);

        
        //get totalprice from calculatController
        double totalPrice = orderFeignClient.calculatePrice(dishDTOs).getBody();
        System.out.println("Calculated Total Price from CalculateServer: " + totalPrice);

        //set totalprice in order
        order.setTotalPrice(totalPrice);

        //save order in database
        Order savedOrder = orderService.saveOrder(order);
        System.out.println("Saved Order: " + savedOrder);
        return new RedirectView("/confirmation?orderId=" + savedOrder.getOrderId());
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable int orderId) {
        Optional<Order> orderOptional = orderService.getOrderById(orderId);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.deleteOrderById(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllOrders() {
        orderService.deleteAllOrders();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
