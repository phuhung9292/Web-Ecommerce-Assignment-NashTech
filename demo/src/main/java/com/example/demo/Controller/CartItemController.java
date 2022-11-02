package com.example.demo.Controller;

import com.example.demo.Dto.ProductDetailOnCartDto;
import com.example.demo.Dto.ProductItemDetail;
import com.example.demo.Dto.ProductItemDto;
import com.example.demo.Entity.TblCartItemEntity;
import com.example.demo.Service.CartItem.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartItemController {
    private CartItemService service;
    @PostMapping("/{productitemid}")
    public ResponseEntity<?> AddToCart(@RequestBody TblCartItemEntity entity, @PathVariable(name = "productitemid") int productItemId){
        return service.save(entity,productItemId);
    }
    @GetMapping()
    public List<ProductDetailOnCartDto> getCart(){
        return service.findAllByCartid();
    }

    @DeleteMapping("/{productid}")
    public ResponseEntity<?> deleteProductOnCart(@PathVariable int productid){
        return service.deleteByProductId(productid);
    }
    @PutMapping("/{productid}")
    public ResponseEntity<?> updateCart(@RequestBody TblCartItemEntity entity,@PathVariable int productid){
        return service.updateQuantityProductItemOnCart(entity,productid);
    }
}
