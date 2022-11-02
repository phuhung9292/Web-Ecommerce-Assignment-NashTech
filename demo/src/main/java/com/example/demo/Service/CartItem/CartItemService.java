package com.example.demo.Service.CartItem;

import com.example.demo.Dto.ProductDetailOnCartDto;
import com.example.demo.Entity.TblCartItemEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartItemService {
    ResponseEntity<?> save(TblCartItemEntity entity, int productItemId);

    List<ProductDetailOnCartDto> findAllByCartid();

    ResponseEntity<?> deleteByProductId(int productid);

    ResponseEntity<?> updateQuantityProductItemOnCart(TblCartItemEntity entity, int productid);
}
