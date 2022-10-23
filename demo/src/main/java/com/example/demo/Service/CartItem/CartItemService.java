package com.example.demo.Service.CartItem;

import com.example.demo.Dto.ProductDetailOnCartDto;
import com.example.demo.Entity.TblCartItemEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartItemService {
    ResponseEntity<?> save(TblCartItemEntity entity, int iduser, int productId, int variation1, int variation2);

    List<ProductDetailOnCartDto> findAllByCartid(int cartId);
}
