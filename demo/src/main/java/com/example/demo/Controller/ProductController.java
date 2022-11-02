package com.example.demo.Controller;

import com.example.demo.Dto.ProductDto;
import com.example.demo.Entity.TblProductEntity;
import com.example.demo.Service.Product.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {
    private ProductService service;

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        return service.findAll();
    }
    @PostMapping()
    public ResponseEntity<TblProductEntity> createProduct(@RequestBody TblProductEntity entity){
        return service.save(entity);
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(@RequestParam String name){
        return service.findByName(name);
    }
//    @PutMapping("/{id}")
//    public
    @GetMapping("/search/cate")
    public ResponseEntity<List<ProductDto>> searchProductsByCateId(@RequestParam int cateid){
        return service.findProductsByCategory(cateid);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody TblProductEntity entity){
        return service.updateProduct(entity);
    }
}
