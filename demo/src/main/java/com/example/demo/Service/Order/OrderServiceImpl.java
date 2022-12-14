package com.example.demo.Service.Order;

import com.example.demo.Dto.OrderDto;
import com.example.demo.Entity.*;
import com.example.demo.Repository.*;
import com.example.demo.security.userPrincal.UserPrinciple;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service @AllArgsConstructor
public class OrderServiceImpl implements OrderService{
    private IOrderHistoryRepository historyRepository;
    private IShopOrderRepository orderRepository;
    private IShoppingCartRepository cartShopingrepo;


    private IUserRepository userRepository;
    private ICartItemRepository cartItemRepository;
    private IProductItemRepository productItemRepository;
    private  IOrderStatusRepository orderStatusRepository;

    @Override
    public ResponseEntity<?> OrderProduct(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        UserPrinciple userPrinciple=new UserPrinciple();

        try {
            userPrinciple= (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

//        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        int id = userPrinciple.getId();

        Date date = new Date();
        TblUserEntity user = userRepository.findById(id).get();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        int cartId= cartShopingrepo.findTblShoppingCartEntityByUserid(user.getId()).getId();

        List<TblCartItemEntity> listCart =cartItemRepository.findAllByCartid(cartId);
        if(listCart.isEmpty()){
            map.put("message","List empty");
            return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
        }
        try {
            TblShopOrderEntity order = new TblShopOrderEntity();
            order.setOrderDate(date);
            order.setShippingAddress(user.getAddress());
            order.setStatus(2);
            order.setUserId(id);
            double total=0;
            for (TblCartItemEntity item:listCart) {
                TblProductItemEntity productItem = productItemRepository.findById(item.getProductItemId()).get();
                if(item.getQuantity() >productItem.getQuantity()){
                    map.put("status",0);
                    map.put("Message","Not enoght product");
                    return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
                }
                total += item.getQuantity() * productItem.getPrice();
            }
            order.setTotal(total);
            TblShopOrderEntity ordered = orderRepository.save(order);

            for (TblCartItemEntity item:listCart) {

                TblOrderHistoryEntity historyOrder= new TblOrderHistoryEntity();
                historyOrder.setOrderId(ordered.getId());
                TblProductItemEntity productItem = productItemRepository.findById(item.getProductItemId()).get();
                historyOrder.setProductItemId(item.getProductItemId());
                historyOrder.setQuantity(item.getQuantity());
                historyOrder.setPrirce(productItem.getPrice());
                productItem.setQuantity(productItem.getQuantity()-item.getQuantity());
                productItemRepository.save(productItem);
                historyRepository.save(historyOrder);
                cartItemRepository.deleteById(item.getId());
            }
//            cartItemRepository.deleteAllByCartid(cartId);
            map.put("status",1);
            map.put("Message","Order Successfully");
            return new ResponseEntity<>(map, HttpStatus.OK);

        }catch (Exception ex){
            map.clear();
            map.put("status",0);
            map.put("Message","Error");
            return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<OrderDto> listAllOrderFromCustomer(){
        List<TblShopOrderEntity> listOrders=orderRepository.findAllByOrderDateDesc();
        List<OrderDto> ordersDto = new ArrayList<>();
        for (TblShopOrderEntity o: listOrders) {
            TblOrderStatusEntity status = orderStatusRepository.findById(o.getStatus()).get();
            OrderDto order= new OrderDto();
            order.setId(o.getId());
            order.setOrderDate(o.getOrderDate());
            order.setTotal(o.getTotal());
            order.setStatus(status.getStatus());
            order.setUserName(userRepository.findById(o.getUserId()).get().getFullName());
            ordersDto.add(order);
        }
        return ordersDto;
    }
    @Override
    public List<OrderDto> listAllOrderOfCustomer(){
        UserPrinciple userPrinciple= (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<TblShopOrderEntity> listOrders=orderRepository.findAllByUserId(userPrinciple.getId());
        List<OrderDto> ordersDto = new ArrayList<>();
        for (TblShopOrderEntity o: listOrders) {
            TblOrderStatusEntity status = orderStatusRepository.findById(o.getStatus()).get();
            OrderDto order= new OrderDto();
            order.setId(o.getId());
            order.setOrderDate(o.getOrderDate());
            order.setTotal(o.getTotal());
            order.setStatus(status.getStatus());
            order.setUserName(userRepository.findById(o.getUserId()).get().getFullName());
            ordersDto.add(order);
        }
        return ordersDto;
    }
    @Override
    public List<TblProductItemEntity> getOrderDetail(int orderId){
        List<TblProductItemEntity> listProductOrder= productItemRepository.findOrderDetail(orderId);
        for (TblProductItemEntity product: listProductOrder) {
            int quantity =historyRepository.findTblOrderHistoryEntitiesByOrderIdAndProductItemId(orderId,product.getId()).getQuantity();
            product.setQuantity(quantity);
        }
        return  listProductOrder;

    }
    @Override
    public TblShopOrderEntity getStatusD(int id){
        return orderRepository.findById(id).get();
    }

    @Override
    public ResponseEntity<?> updateStatusOrder(int id, int statusId){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        TblShopOrderEntity shopOrder = orderRepository.findById(id).get();
        shopOrder.setStatus(statusId);
        orderRepository.save(shopOrder);
        map.put("Status","Success");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
//    public <S extends TblOrderHistoryEntity> S save(S entity) {
//        return historyRepository.save(entity);
//    }
}
