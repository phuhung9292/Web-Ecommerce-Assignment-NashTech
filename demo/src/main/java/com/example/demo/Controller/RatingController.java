package com.example.demo.Controller;

import com.example.demo.Entity.TblRatingEntity;
import com.example.demo.Service.Rating.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
@AllArgsConstructor
public class RatingController {
    private RatingService service;

    @PostMapping()
    public ResponseEntity<?> userRating(@RequestBody TblRatingEntity entity){
        return service.UserOrderRatingProduct(entity);
    }
    @GetMapping("/{productId}")
    public List<TblRatingEntity> getAllRatingByProductId(@PathVariable int productId){
        return service.productGetAllRating(productId);
    }
}
