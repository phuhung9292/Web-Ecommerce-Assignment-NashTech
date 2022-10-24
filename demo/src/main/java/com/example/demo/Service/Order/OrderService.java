package com.example.demo.Service.Order;

import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<?> OrderProduct(int userId);
}
