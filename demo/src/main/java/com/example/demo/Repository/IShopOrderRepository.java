package com.example.demo.Repository;

import com.example.demo.Entity.TblShopOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IShopOrderRepository extends JpaRepository<TblShopOrderEntity,Integer> {
//    List<TblShopOrderEntity> findAllByOrderDateDesc();
}
