package com.example.demo.Service.Rating;

import com.example.demo.Entity.TblRatingEntity;
import com.example.demo.Repository.IProductItemRepository;
import com.example.demo.Repository.IRatingRepository;
import com.example.demo.security.userPrincal.UserPrinciple;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RatingImplService implements RatingService {
    private IRatingRepository ratingRepository;
    private IProductItemRepository productItemRepository;

    @Override
    public ResponseEntity<?> UserOrderRatingProduct(TblRatingEntity rating){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        UserPrinciple userPrinciple= (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        rating.setIsActice(true);
        int productId = productItemRepository.findTblProductItemEntityById(rating.getOrderedProductId()).getProductId();
        rating.setOrderedProductId(productId);
        rating.setUserid(userPrinciple.getId());
        ratingRepository.save(rating);
        map.put("message","Success");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public List<TblRatingEntity> productGetAllRating(int productid){
        return ratingRepository.findAllByOrderedProductId(productid);
    }
}
