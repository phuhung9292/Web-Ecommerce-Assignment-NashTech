package com.example.demo.Service.Rating;

import com.example.demo.Entity.TblRatingEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RatingService {
    ResponseEntity<?> UserOrderRatingProduct(TblRatingEntity rating);

    List<TblRatingEntity> productGetAllRating(int productid);
}
