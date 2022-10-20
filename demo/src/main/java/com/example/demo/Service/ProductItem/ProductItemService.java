package com.example.demo.Service.ProductItem;

import com.example.demo.Entity.TblProductItemEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductItemService {
    List<TblProductItemEntity> findAll();

    ResponseEntity<TblProductItemEntity> save(TblProductItemEntity entity);

    ResponseEntity<?> updateProductItem(TblProductItemEntity entity);
}
