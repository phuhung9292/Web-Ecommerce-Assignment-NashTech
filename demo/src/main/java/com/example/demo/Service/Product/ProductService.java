package com.example.demo.Service.Product;

import com.example.demo.Dto.ProductDto;
import com.example.demo.Entity.TblProductEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<List<ProductDto>> findAll();

    ResponseEntity<TblProductEntity>  save(TblProductEntity entity);

    ResponseEntity<List<ProductDto>> findByName(String s);

    ResponseEntity<List<ProductDto>> findProductsByCategory(int id);

    ResponseEntity<?> updateProduct(TblProductEntity entity);

    TblProductEntity getById(int id);
}
