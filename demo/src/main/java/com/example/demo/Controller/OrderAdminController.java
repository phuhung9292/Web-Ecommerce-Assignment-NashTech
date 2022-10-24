package com.example.demo.Controller;

import com.example.demo.Entity.TblShopOrderEntity;
import com.example.demo.Service.Order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/order")
@AllArgsConstructor
public class OrderAdminController {
    private OrderService orderService;
    @GetMapping()
    public List<TblShopOrderEntity> getAllOrder(){
        return orderService.listAllOrderFromCustomer();
    }
}
