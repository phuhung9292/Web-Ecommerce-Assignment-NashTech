package com.example.demo.Controller;

import com.example.demo.Dto.OrderDto;
import com.example.demo.Service.Order.OrderService;
import com.example.demo.Service.Order.OrderServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
//@NoArgsConstructor
public class OrderUserController {
    @Autowired
    private final OrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> userOrder(@PathVariable int userId){
        return orderService.OrderProduct(userId);
    }
    @GetMapping("/{id}")
    public List<OrderDto> getAllOrderOfUserById(@PathVariable int id){
        return orderService.listAllOrderOfCustomer(id);
    }
}