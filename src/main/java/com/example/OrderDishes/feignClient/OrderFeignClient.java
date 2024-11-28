package com.example.OrderDishes.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.OrderDishes.model.DishDTO;

@FeignClient(name = "CalculatePrice", url= "http://localhost:8102")
public interface OrderFeignClient {
	
	@PostMapping("/calculate")
	ResponseEntity<Double> calculatePrice(@RequestBody List<DishDTO> dishes);
}
