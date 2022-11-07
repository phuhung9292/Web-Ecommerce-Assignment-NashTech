package com.example.demo.Service.Order;

import com.example.demo.Dto.OrderDto;
import com.example.demo.Entity.TblProductItemEntity;
import com.example.demo.Entity.TblShopOrderEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    ResponseEntity<?> OrderProduct();

    List<OrderDto> listAllOrderFromCustomer();

    List<OrderDto> listAllOrderOfCustomer();

    List<TblProductItemEntity> getOrderDetail(int orderId);

    TblShopOrderEntity getStatusD(int id);

    ResponseEntity<?> updateStatusOrder(int id, int statusId);
}
