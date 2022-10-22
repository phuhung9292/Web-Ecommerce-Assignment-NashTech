package com.example.demo.Service.ProductItem;

import com.example.demo.Dto.ProductItemDetail;
import com.example.demo.Entity.TblProductItemEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductItemService {
    List<TblProductItemEntity> findAll();

    ResponseEntity<?> save(TblProductItemEntity entity,int idVariation1, int idVariation2);

    ResponseEntity<?> updateProductItem(TblProductItemEntity entity);

    ResponseEntity<?> findById(Integer integer);

    ResponseEntity<?> findByProductId(Integer integer);

    ProductItemDetail findByProductIdAndSizeIdAndColor(Integer productId, Integer varation1, Integer variation2);
}
