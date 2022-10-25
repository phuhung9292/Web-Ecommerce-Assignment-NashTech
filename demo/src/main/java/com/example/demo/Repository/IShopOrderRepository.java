package com.example.demo.Repository;

import com.example.demo.Entity.TblShopOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IShopOrderRepository extends JpaRepository<TblShopOrderEntity,Integer> {
//    List<TblShopOrderEntity> findAllByOrderDateDesc();
    @Query(value = "select * from tbl_shop_Order Order by id desc",nativeQuery = true)
    List<TblShopOrderEntity> findAllByOrderDateDesc();
    @Query(value = "select * from tbl_shop_Order Where userId = ?1 Order by id desc",nativeQuery = true)
    List<TblShopOrderEntity> findAllByUserId(int userId);

}
