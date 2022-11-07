package com.example.demo.Controller;

import com.example.demo.Dto.OrderDto;
import com.example.demo.Entity.TblOrderStatusEntity;
import com.example.demo.Entity.TblShopOrderEntity;
import com.example.demo.Repository.IOrderStatusRepository;
import com.example.demo.Service.Order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/order")
@AllArgsConstructor
public class OrderAdminController {
    private OrderService orderService;
    private IOrderStatusRepository statusRepository;
    @GetMapping()
    public List<OrderDto> getAllOrder(){
        return orderService.listAllOrderFromCustomer();
    }

    @PutMapping("/{id}/{statusId}")
    public ResponseEntity<?> adminUpdateStatusOrder(@PathVariable int id, @PathVariable int statusId) {
        return orderService.updateStatusOrder(id,statusId);
    }
    @GetMapping("/getAllStatus")
    public List<TblOrderStatusEntity> getAllStatus(){
        return statusRepository.findAll();
    }
}
