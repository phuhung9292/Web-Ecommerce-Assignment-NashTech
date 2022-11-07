package com.example.demo.Repository;

import com.example.demo.Entity.TblRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IRatingRepository extends JpaRepository<TblRatingEntity,Integer> {
    List<TblRatingEntity> findAllByOrderedProductId(int productId);
}
