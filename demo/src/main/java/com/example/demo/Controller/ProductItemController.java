package com.example.demo.Controller;

import com.example.demo.Entity.TblProductItemEntity;
import com.example.demo.Service.ProductItem.ProductItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productitem")
@AllArgsConstructor
public class ProductItemController {
    private final ProductItemService service;

    @GetMapping()
    public ResponseEntity<List<TblProductItemEntity>> getAllProductItem(){
        return ResponseEntity.ok().body(service.findAll());
    }
    @PostMapping()
    public ResponseEntity<TblProductItemEntity> createProduct(@RequestBody TblProductItemEntity entity){
        return service.save(entity);
    }
    @PutMapping()
    public ResponseEntity<?> updateProduct(@RequestBody TblProductItemEntity entity){
        return service.updateProductItem(entity);
    }
}
