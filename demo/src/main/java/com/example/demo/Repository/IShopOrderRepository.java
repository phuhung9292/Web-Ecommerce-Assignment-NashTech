package com.example.demo.Repository;

import com.example.demo.Entity.TblShopOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShopOrderRepository extends JpaRepository<TblShopOrderEntity,Integer> {
}
