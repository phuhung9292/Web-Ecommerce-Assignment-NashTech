package com.example.demo.Controller;

import com.example.demo.Dto.ProductItemDetail;
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
//    @PostMapping()
//    public ResponseEntity<TblProductItemEntity> createProduct(@RequestBody TblProductItemEntity entity){
//        return service.save(entity);
//    }
    @PutMapping()
    public ResponseEntity<?> updateProduct(@RequestBody TblProductItemEntity entity){
        return service.updateProductItem(entity);
    }
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody TblProductItemEntity entity,@RequestParam(name="productId") int productid,@RequestParam (name="variation1") int idVariation1,@RequestParam(name="variation2") int idVariation2){
        return service.save(entity,idVariation1,idVariation2,productid);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
       return service.findByProductId(id);
    }

    @GetMapping("/{productId}/{idvariation1}/{idvariation2}")
    public ProductItemDetail getProductItemByProductIdAndSizeIdAndColorId(@PathVariable(name="productId") Integer productId,@PathVariable(name="idvariation1") Integer variationid1,@PathVariable(name="idvariation2") Integer idvariation2){
        return service.findByProductIdAndSizeIdAndColor(productId,variationid1,idvariation2);
    }
}
