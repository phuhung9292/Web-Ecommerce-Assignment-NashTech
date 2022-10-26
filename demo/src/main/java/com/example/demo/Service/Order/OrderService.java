package com.example.demo.Service.Order;

import com.example.demo.Dto.OrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    ResponseEntity<?> OrderProduct();

    List<OrderDto> listAllOrderFromCustomer();

    List<OrderDto> listAllOrderOfCustomer();
}
