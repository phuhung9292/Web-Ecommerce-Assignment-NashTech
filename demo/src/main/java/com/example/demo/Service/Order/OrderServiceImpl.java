package com.example.demo.Service.Order;

import com.example.demo.Entity.*;
import com.example.demo.Repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service @AllArgsConstructor
public class OrderServiceImpl implements OrderService{
    private IOrderHistoryRepository historyRepository;
    private IShopOrderRepository orderRepository;
    private IShoppingCartRepository cartShopingrepo;

    private IUserRepository userRepository;
    private ICartItemRepository cartItemRepository;
    private IProductItemRepository productItemRepository;

    @Override
    public ResponseEntity<?> OrderProduct(int userId){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Date date = Calendar.getInstance().getTime();
        TblUserEntity user = userRepository.findById(userId).get();

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
            order.setUserId(userId);
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

//    public <S extends TblOrderHistoryEntity> S save(S entity) {
//        return historyRepository.save(entity);
//    }
}
