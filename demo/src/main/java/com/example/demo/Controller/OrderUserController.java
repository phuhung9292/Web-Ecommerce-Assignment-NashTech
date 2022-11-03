package com.example.demo.Controller;

import com.example.demo.Dto.OrderDto;
import com.example.demo.Entity.TblProductItemEntity;
import com.example.demo.Entity.TblShopOrderEntity;
import com.example.demo.Service.Order.OrderService;
import com.example.demo.Service.Order.OrderServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
//@NoArgsConstructor
public class OrderUserController {
    @Autowired
    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<?> userOrder(){
        return orderService.OrderProduct();
    }
    @GetMapping("")
    public List<OrderDto> getAllOrderOfUserById(){
        return orderService.listAllOrderOfCustomer();
    }
    @GetMapping("/detail/{orderId}")
    public List<TblProductItemEntity> getOrderDetailByOrderId(@PathVariable int orderId){
        return orderService.getOrderDetail(orderId);
    }
    @GetMapping("/status/{orderId}")
    public TblShopOrderEntity getStatus(@PathVariable int orderId){
        return orderService.getStatusD(orderId);
    }
}
