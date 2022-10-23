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
    @PostMapping("/addtocart/{userid}/{productid}/{size}/{color}")
    public ResponseEntity<?> AddToCart(@RequestBody TblCartItemEntity entity, @PathVariable(name = "productid") int productId,@PathVariable(name = "size") int sizeId,@PathVariable (name = "color") int colorId,@PathVariable(name = "userid") int userId){
        return service.save(entity,userId,productId,sizeId,colorId);
    }
    @GetMapping("{userid}")
    public List<ProductDetailOnCartDto> getCart(@PathVariable int userid){
        return service.findAllByCartid(userid);
    }

    @DeleteMapping("/{productid}/{userid}")
    public ResponseEntity<?> deleteProductOnCart(@PathVariable int productid,@PathVariable int userid){
        return service.deleteByProductId(productid,userid);
    }
    @PutMapping("/{userid}/{productid}")
    public ResponseEntity<?> updateCart(@RequestBody TblCartItemEntity entity,@PathVariable int userid,@PathVariable int productid){
        return service.updateQuantityProductItemOnCart(entity,productid,userid);
    }
}
