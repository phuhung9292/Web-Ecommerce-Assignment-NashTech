package com.example.demo.Service.Order;

import com.example.demo.Entity.TblShopOrderEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    ResponseEntity<?> OrderProduct(int userId);

    List<TblShopOrderEntity> listAllOrderFromCustomer();
}
