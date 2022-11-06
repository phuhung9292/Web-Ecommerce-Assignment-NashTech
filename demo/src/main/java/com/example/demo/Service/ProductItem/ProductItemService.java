package com.example.demo.Service.ProductItem;

import com.example.demo.Dto.ProductItemDetail;
import com.example.demo.Entity.TblProductItemEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductItemService {
    List<TblProductItemEntity> findAll();

    ResponseEntity<?> save(TblProductItemEntity entity,int idVariation1, int idVariation2,int productId);

    ResponseEntity<?> updateProductItem(TblProductItemEntity entity);

    TblProductItemEntity findById(Integer integer);

    ResponseEntity<?> findByProductId(Integer integer);

    ProductItemDetail findByProductIdAndSizeIdAndColor(Integer productId, Integer varation1, Integer variation2);

    List<TblProductItemEntity> adminGetProductItemById(int id);
}
