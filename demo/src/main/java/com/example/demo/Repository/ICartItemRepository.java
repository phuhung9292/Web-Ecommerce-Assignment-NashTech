package com.example.demo.Repository;

import com.example.demo.Entity.TblCartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartItemRepository extends JpaRepository<TblCartItemEntity,Integer> {
    List< TblCartItemEntity> findAllByCartid(int cartId);

    TblCartItemEntity findTblCartItemEntityByProductItemIdAndCartid(int productId,int cartId);

    void deleteById(int id);
//    void deleteAllByCartid(int id);

}
